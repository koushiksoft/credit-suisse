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



#### To achive this I have used below librares/ reposetories.

- Swagger2 & Swagger UI : API documentations and Rest API call
- slf4J : Log.
- lombok : Boiler code.
- h2database : For inmemory database.
- mockito : For test cases


## FEW REQUIRD URLS

1. API documentations and Rest API call
> http://localhost:8080/swagger-ui.html

2. Database access
> http://localhost:8080/h2-console/   (user: sa and password: password)

