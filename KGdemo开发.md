## neo4j部署

选用的neo4j版本为3.5.17

下载 docker pull neo4j:3.5.17  

 1.创建docker容器

在neo4j目录下先创建两个子目录 一个data 用来挂载数据库文件，一个import 用来存放导入的csv文件

执行命令：
```docker run -itd --name neo4j -p 7474:7474 -p 7687:7687 --env=NEO4J_AUTH=none -v /home/cad/neo4j/import:/var/lib/neo4j/import -v /home/cad/neo4j/data:/data neo4j```

2.删除data/databases/graph.db/ 下的所有文件

因为neo4j-admin import 只能在空库下执行

3.将知识图谱的相关csv文件放在import文件夹下

4.导入（示例）

```docker exec -it neo4j bash```

```bin/neo4j-admin import --id-type=STRING ```

```--nodes=import/人物.csv ```

```--nodes=import/组织.csv ```

``` --relationships=import/relations.csv```

示例数据如下：

| 人物:ID       | name          | 描述                                                         | 性别              | 别名 | :LABEL | :LABEL |
| ------------- | ------------- | ------------------------------------------------------------ | ----------------- | ---- | ------ | ------ |
| 王晓初        | 王晓初        | 1976年12月参加工作，中共党员，教授级高级工程师。1989年毕业于北京邮电学院，2005年获得香港理工大学工商管理博士学位。 | 男                |      | 人物   | Base   |
| 陆益民        | 陆益民        | 1985年8月参加工作，中共党员，研究员级高级工程师。            | 男                |      | 人物   | Base   |
| 安东尼奥·穆齐 | 安东尼奥·穆齐 | 男                                                           | [安东尼奥·梅乌奇] | 人物 | Base   |        |
| 亚历山大·贝尔 | 亚历山大·贝尔 | 男                                                           | [贝尔]            | 人物 | Base   |        |
| Grace Hopper  | Grace Hopper  | 女                                                           |                   | 人物 | Base   |        |

:LABEL表明该实体的标签。设计两个标签是因为代码中所有实体类的基于Base。

5.退出容器  exit

6.重启容器 docker restart neo4j

**注：导入数据这里有点问题，导入之后容器会挂掉，restart之后立刻挂起。原因还未知晓。**

部署好了之后可以访问39.102.46.180:7474，查看图谱。

## 项目依赖与配置

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-neo4j</artifactId>
</dependency>
```

```java
spring:
  data:
    neo4j:
      uri: bolt://39.102.46.180:7687
      username: neo4j
      password: neo4j
```

## 工程文件

#####entity-kgEntity包

存放了知识图谱所需的实体与关系类。

为了配合知识图谱问答模型的设计，知识图谱实体目前划分为如下类：称号，地点，电话号码，工程，国家，技术，节日，金额，科技产品，排名，品牌，任务，荣誉，时间，事件，数量，文件，行业，业务，组织。kgdemo开发阶段仅实现人物与组织两类实体进行测试。Person与Organization类都基于Base类。

```java
@Data
public class Base {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "id", required = true)
    protected Long id;

    @Property(name = "name")
    @ApiModelProperty(value = "姓名", required = true)
    protected String name;
}
```

实体之间的关系类type定义为relation，为关系添加属性name，来区分不同的关系。关系连接着Base实体。

```java
@Data
@RelationshipEntity(type="relation")
public class Relation {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String relation;

    @StartNode
    private Base start;
    @EndNode
    private Base end;
}
```



##### dao-kgdao包

持久层，与neo4j数据库进行交互。

继承Neo4jRepository可以实现简单的增删改查操作。在此基础上，

在节点部分设计两个查询：通过name返回节点信息；通过name返回邻居节点。

```java
public interface NodeRepository extends Neo4jRepository<Base, Long> {
    @Query("MATCH (n{name:{name}}) RETURN n")
    Base findByName(@Param("name") String name);

    @Query("MATCH (n{name:{name}})-[r:relation]->(m) RETURN m")
    List<Base> findNeighbor(@Param("name") String name);
}
```

在关系部分设计一个查询：通过name返回所有邻接的节点与关系（注意有向性）。

```java
public interface RelationRepository extends Neo4jRepository<Relation, Long> {
    //通过名字 返回节点n以及n指向的所有节点与关系
    //@Query("MATCH p=(n:Person)-[r:Relation]->(m:Person) WHERE id(n)={0} RETURN p")
    @Query("MATCH p=(n{name:{name}})-[r:relation]->(m) RETURN p")
    List<Relation> findAllRel(@Param("name") String name);
```

更多复杂的操作可学习Cypher语言+@Query注解即可实现。

##### service-kgSercive

业务层，负责业务逻辑实现，调用持久层的接口。

```
@Service
public class KGServiceImpl implements KGServer {
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    RelationRepository relationRepository;
    ......
```

在这里我们实现了增删改查与批量上传节点、批量上传关系功能。

注：添加和修改节点都需要区分是哪类节点（因为有不同的属性）。上传节点同样如此，上传需要区分节点类，一个文件内包含的是一类节点。目前demo仅开发Person类的添加修改与上传。

上传节点文件应对应该实体类的属性。如Person：

| name           | desc                                                         | sex               | otherName |
| -------------- | ------------------------------------------------------------ | ----------------- | --------- |
| 王晓初         | 1976年12月参加工作，中共党员，教授级高级工程师。1989年毕业于北京邮电学院，2005年获得香港理工大学工商管理博士学位。 | 男                |           |
| 陆益民         | 1985年8月参加工作，中共党员，研究员级高级工程师。            | 男                |           |
| 安东尼奥·穆齐  | 男                                                           | [安东尼奥·梅乌奇] |           |
| 亚历山大·贝尔  | 男                                                           | [贝尔]            |           |
| Grace   Hopper | 女                                                           |                   |           |

上传关系文件为:（示例）

| name1 | name2  | relation |
| ----- | ------ | -------- |
| 李四  | 王五   | 朋友     |
| 李四  | 邮电部 | 工作     |

##### controller-KGController

控制层，接受前端传来的数据，并返回业务层的数据。

```
@Api(tags = "KG相关接口") //一个tag
@RestController
@RequestMapping("/kg")
public class KGController {
    @Autowired
    private KGServer kgServer;
    ......
```

实现与业务层功能对应的接口。