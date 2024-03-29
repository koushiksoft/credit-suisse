###  Design a simple Social Media application(Maven Project)


#### Users should be able to:

1.       create new posts

2.       follow/unfollow another user

3.       view the 20 most recent posts in the user's news feed.


#### Design should support the following methods:

1. Compose a new post
> createPost(userId, postId, content)

2. Retrieve the 20 most recent post ids in the user's news feed. Each item in the news feed must be posted either by one of the user’s followees or by the user herself. Posts must be ordered by posting time starting from the most recent one.
> getNewsFeed(userId)

3. Follower follows a followee
>follow(followerId, followeeId)

4. Unfollower follows a followee
>unfollow (followerId, followeeId)



#### To achieve this I have used below libraries/ repositories.

- Swagger2 & Swagger UI : API documentations and Rest API call
- slf4J : Log.
- lombok : Boiler code.
- h2database : For in memory database.
- Junit & mockito : For test cases


## CHECK MY TEST CASES

> All test cases are available [here](https://github.com/koushiksoft/credit-suisse/tree/master/socialMedia/src/test/java/com/hello2pal/socialMediaApp).

### FEW REQUIRED URLS

1. API documentations and Rest API call
> http://localhost:8080/swagger-ui.html

2. Database access
> http://localhost:8080/h2-console/   (user: sa and password: password)

### FEATURES URLS

1. createPost
> http://localhost:8080/api/v1.0/post [POST]

Body:
```
{
	"postId":"1111",
	"userId":"U1003",
	"content":"U1003 Spring Frame Work Description Three"
}
```
Header:
```
Content-Type: application/json
```

2. getNewsFeed(userId)
(limit & offset are optional)
> http://localhost:8080/api/v1.0/user/U1003/newsfeed?limit=20&offset=0 [GET]

Header:
```
Content-Type: application/json
```

3. follow(followerId, followeeId)
> http://localhost:8080/api/v1.0/subscriptions [POST]

Body:
```
{
	"followerID":"U1002",
	"followingID":"U1005"
}
```
Header:
```
Content-Type: application/json
```

4. unfollow(followerId, followeeId):
> http://localhost:8080/api/v1.0/subscriptions [DELETE]

Body:
```
{
	"followerID":"U1002",
	"followingID":"U1005"
}
```
Header:
```
Content-Type: application/json
```
