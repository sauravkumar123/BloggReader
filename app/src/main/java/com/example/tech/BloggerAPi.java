package com.example.tech;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerAPi {

    private static final String key ="AIzaSyCk5Mw5EPh0URCQs1nIlP4P8-cguxUYskY";
    private static final String url="https://www.googleapis.com/blogger/v3/blogs/4120096630353699193/posts/";

    private static PostService postService=null;
     static PostService getservice()
    {
        if(postService==null)
        {
            Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            postService=retrofit.create(PostService.class);

        }
        return  postService;
    }
      static interface PostService
    {
        @GET("?key="+key)
        Call<PostList>getPostList();


    }
}


