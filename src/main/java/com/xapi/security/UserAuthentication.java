package com.xapi.security;

public final class UserAuthentication {

}

// https://developer.yodlee.com/API_Resources/Integration_Guide#AccesstoYodleeAPIs

/*

//Request Example - using Sandbox Enviornment
POST /ysl/restserver/v1/user/login HTTP/1.1
Host: developer.api.yodlee.com
Accept: application/json
Content-Type: application/json
Authorization: {cobSession=08062013_2:7af6ff3f739965e72c8b7248c96a2317958f2b2c1a0a1bfa26394c25747e6b18b4e118e95d499058cbdd9212972d70cd41cbc7139934b66cbd65e220712a23ed}
Content-Length: 138
Origin: https://developer.yodlee.com
Connection: keep-alive

{
    "user":      {
      "loginName": "user_login_name",
      "password": "user_password#123",
      "locale": "en_US"
     }
}

//Response example
{
  "user": {
    "id": 10060702,
    "loginName": "user_login_name",
    "name": {
      "first": "John",
      "last": "Doe"
    },
    "session": {
      "userSession": "08062013_1:4daf4cf98182725d6fd018fa8d3165b73511a9d8f4c78f7fc65addfdb8288a0f451b49622d54635e681d3de153a73d608eaf9de7d41a23119cd249cb86d927c2"
    },
    "preferences": {
      "currency": "USD",
      "timeZone": "PST",
      "dateFormat": "MM/dd/yyyy"
    }
  }
}

 */