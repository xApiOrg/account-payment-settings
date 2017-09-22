package com.xapi.security;

public final class CobrandAuthentication {

}

// https://developer.yodlee.com/API_Resources/Integration_Guide#AccesstoYodleeAPIs

/*
//Request Example - using Sandbox Environment
POST /ysl/restserver/v1/cobrand/login HTTP/1.1
Host: developer.api.yodlee.com
Accept: application/json
Content-Type: application/json
Content-Length: 164
Connection: keep-alive
{
    "cobrand":      {
      "cobrandLogin": "cobrand_user_name",
      "cobrandPassword": "cobrand_password",
      "locale": "en_US"
     }
}

//Response
{
  "cobrandId": 10010352,
  "applicationId": "3A4CAE9B71A1CCD7FF41F51006E9ED00",
  "locale": "en_US",
  "session": {
    "cobSession": "08062013_0:0d1ee7eb871b4e48b31bb553b459ca661f66bca0928fdef32ba673c61bb11c92a402b2341b97ca39fdd4a2b3e168d8ca90f66dd115eeee5e797660165f6cf8dd"
  }
}
 */
