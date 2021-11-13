namespace java com.rpc.user.userservice.rpc

struct User {
    1:i64 id,   // id
    2:string loginName, // 登录名称
    3:string name, // 用户名
    4:string password, // 密码
    5:string email, // 邮箱
    6:string phone
}

/*
* 用户服务
* */
service UserService {

    /*
    * 获取用户，通过用户 id
    * */
    User getUserById(1:i64 id);

    User getUserByLoginName(1:string loginName);

    string getUserNameByLoginName(1:string loginName);

    string getUserNameById(1:i64 id);
}