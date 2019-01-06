# travelTogetherAPI
后台服务器
### 一、实体类
##### 1 用户
```
UserInfo {
    private String account;
    private String name;
    private Integer gender;
    private Integer age;
    private Integer in;
    private Integer activity_id;
    private String city;
    private Integer status;
    private String code;
    private String passwd;
    private Integer num_Of_score;
    private Integer score;
}
```
##### 2 活动
```
ActivityInfo {
    private Integer aid;
    private Integer status;
    private String owner;
    private String city;
    private String location;
    private String title;
    private String details;
    private String time_start;
    private String time_end;
    private Integer score;
    private Integer num_of_score;
    private String type;
}
```

### 二、返回格式
##### 1 返回数据
```
private int resCode;//返回码
private String resMsg;//返回信息
private Map<String, Object> data;//数据源
* 返回码码记录：
     * @param resCode--返回码
     * @param resMsg---404服务器内部异常时提示消息(返回码不是404时传空即可)
     * @param map------数据源
     *
     * 0--------success 请求成功
     * 1001-----Parameter error 参数错误
     * 1002-----Empty content 查询结果为空（登录说明账户密码错误）
     * 1003-----Account exists 注册时用户名存在
     * 1004-----Upload file is empty 上传文件为空
     * 404------Exception throw error 捕获Exception
```
##### 2 返回数据样例
```
{
    "resCode": 0,
    "resMsg": "success",
    "data": {
        "content": {
            "account": "123@qq.com",
            "name": "123",
            "gender": 0,
            "age": 18,
            "in": 0,
            "activity_id": 0,
            "city": "南京",
            "status": 0,
            "code": null,
            "passwd": "123",
            "num_Of_score": 1,
            "score": 100
        }
    }
}
```

### 三、API说明
```
ip地址:8888 + ...
如：localhost:8888/api/login?account=123@qq.com&passwd=123
```
##### 1 登录
```
GET /api/login?account=value&passwd=value
```
##### 2 注册
```
POST /api/register
需要的数据
account,name,passwd
```
##### 3 修改用户信息
```
POST /api/updateUserInfo
需要的数据
name,gender,age,city,code,passwd,account
ps:gender的值为0或1，0表示男，1表示女
```
##### 4 查询某用户刚创建待审核的活动
```
GET /api/userCreatedActivities
需要的数据
owner
```
##### 5 查询某用户创建已验证发布的活动
```
GET /api/userPublishedActivities
需要的数据
owner
```
##### 6 查询某用户创建已结束的活动
```
GET /api/userFinishedActivities
需要的数据
owner
```
##### 7 查询某用户被撤销的活动
```
GET /api/userRepealActivities
需要的数据
owner
```
##### 8 查询所有已验证发布可参加的活动
```
GET /api/publishedActivities

```
##### 9 查询某种可参加类别的活动
```
GET /api/typeActivities
需要的数据
type
```
##### 10 管理人员查询所有待验证发布的活动
```
GET /api/toPublishedActivities
```
##### 11 用户创建活动
```
GET /api/addActivity
需要的数据
owner,city,location,title,details,time_start,time_end,type
ps：time_start、time_end格式为2019-01-01
```
