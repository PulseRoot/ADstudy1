package com.example.myjsontest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
"id":1,
"title":"꾼",
"title_eng":"The Swindlers",
"date":"2017-11-22",
"user_rating":3.9,
"audience_rating":8.36,
"reviewer_rating":4.33,
"reservation_rate":61.69,
"reservation_grade":1,
"grade":15,
"thumb":"http://movie2.phinf.naver.net/20171107_251/1510033896133nWqxG_JPEG/movie_image.jpg?type=m99_141_2",
"image":"http://movie.phinf.naver.net/20171107_251/1510033896133nWqxG_JPEG/movie_image.jpg"
*/

public class MovieList{

    private String message;
    private String code;
    private String resultType;
    private List<Result> result;

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getResultType(){
        return resultType;
    }
    public void setResultType(String resultType){
        this.resultType = resultType;
    }
    public List<Result> getitems(){
        return result;
    }
    public void setitems(List<Result> result){
        this.result = result;
    }

}

