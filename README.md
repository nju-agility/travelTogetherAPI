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
##### 1 登录(本地不存在Token的情况)
```
GET /api/login?account=value&passwd=value
请求成功返回的数据为：
{
    "resCode": 0,
    "resMsg": "success",
    "data": {
        "name": "zhangsan",
        "account": "123@qq.com",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjNAcXEuY29tIiwicm9sZXMiOiJtZW1iZXIiLCJpYXQiOjE1NDcwOTQ1MDR9.3mzz8v1nc0dgryHy4NtnKoGCVtui-EeRLR30RovNwCE"
    }
}
```
##### 2 注册
```
POST /api/register
----------------------------
需要的数据
account,name,passwd
----------------------------
返回的数据
{
    "resCode": 0,
    "resMsg": "success",
    "data": null
}
```
##### 3 修改用户信息
```
POST /api/updateUserInfo
需要的数据
name,gender,age,city,code,passwd,account
ps:gender的值为0或1，0表示男，1表示女
----------------------------
返回的数据
{
    "resCode": 0,
    "resMsg": "success",
    "data": null
}
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
----------------------------
返回的数据
{
    "resCode": 0,
    "resMsg": "success",
    "data": {
        "content": [
            {
                "aid": 1,
                "status": 1,
                "owner": "123@qq.com",
                "city": "南京",
                "location": "鼓楼",
                "title": "1",
                "details": "1",
                "time_start": "2019-01-01",
                "time_end": "2020-01-01",
                "score": 100,
                "num_of_score": 1,
                "type": "outgoing"
            },
            {
                "aid": 10,
                "status": 1,
                "owner": "123@qq.com",
                "city": "南京",
                "location": "鼓楼",
                "title": "逛街",
                "details": "qw",
                "time_start": "2019-01-06",
                "time_end": "2019-01-07",
                "score": 100,
                "num_of_score": 1,
                "type": "花钱"
            }
        ]
    }
}
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
owner,city,location,title,details,time_start,time_end,type,price
ps：time_start、time_end格式为2019-01-01
```
##### 12 登录(本地存在Token的情况)
```
GET /api/secure/login
ps：请求的Headers中，需要将Authorization设置为："Bearer "+Token
    Content-Type设置为：application/json

返回的数据
失败：
{
    "timestamp": "2019-01-11T03:48:24.748+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Missing or invalid Authorization header",
    "path": "/api/secure/login"
}
成功：
{
    "resCode": 0,
    "resMsg": "success",
    "data": null
}
```
##### 13 获取用户信息
```
GET /api/userInfo
需要的数据
account

返回的数据
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
            "passwd": "****",
            "num_Of_score": 1,
            "score": 100
        }
    }
}
```
##### 14 按城市查询可参加的所有活动
```
GET /api/cityActivities
需要的数据
city
```
##### 15 按城市查询可参加的某个类别的活动
```
GET /api/cityAndTypeActivities
需要的数据
city,type
```
##### 16 管理员登录
```
GET /api/adminLogin
需要的数据
accout,passwd
```
##### 17 管理员验证发布或取消一个活动
```
POST /api/checkActivity
需要的数据
aid,status
ps：传入的status应为1或3，1代表审核通过，3代表审核不通过
```
##### 18 管理员验证发布或取消多个活动
```
POST /api/checkActivities
需要的数据类似如下格式
[
	{
		"aid":1,
		"status":1
	},
	{
		"aid":9,
		"status":5
	}
]
```
##### 19 上传一张图片
```
POST /api/uploadImg
----------------------------
需要的数据
file、accout、item
ps：account为唯一标识，用户头像为account
活动为aid
item的值为0，1，2，3
0-->头像    1-->学生证照等
2-->评论图  3-->活动图片
----------------------------
返回数据
{
    "resCode": 0,
    "resMsg": "success",
    "data": null
}
```
##### 20 用户申请加入活动
```
POST /api/userApplyActivity
需要的数据
accout、activity_id
```
##### 21 查看用户正在进行的活动
```
POST /api/userAttendActivity
需要的数据
activity_id
```
##### 22 查看用户参加过的活动
```
POST /api/getRecord
需要的数据
account
```
##### 23 用户退出活动
```
POST /api/userQuitActivity
需要的数据
account
```