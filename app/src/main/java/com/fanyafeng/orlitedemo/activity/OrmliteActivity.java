package com.fanyafeng.orlitedemo.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.fanyafeng.orlitedemo.BaseActivity;
import com.fanyafeng.orlitedemo.R;
import com.fanyafeng.orlitedemo.bean.Article;
import com.fanyafeng.orlitedemo.bean.Student;
import com.fanyafeng.orlitedemo.bean.User;
import com.fanyafeng.orlitedemo.db.ArticleDao;
import com.fanyafeng.orlitedemo.db.StudentDao;
import com.fanyafeng.orlitedemo.db.UserDao;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class OrmliteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ormlite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_create_orm_user:
                Log.d("TAG", "创建user");
                createUser();
                break;
            case R.id.btn_create_orm_userarticle:
                createUserArticle();
                break;
            case R.id.btn_getarticle_by_id:
                getArticleByid();
                break;
            case R.id.btn_getarticle_byid_withuser:
                getArticleByArticleId();
                break;
            case R.id.btn_getarticle_by_userid:
                getArticleByUserId();
                break;
            case R.id.btn_getuser_by_id:
                getUserByid(1);
                break;
            case R.id.btn_create_student:
                createStudent();
                break;
            case R.id.btn_student_like:
                queryStudent();
                break;
            case R.id.btn_student_order:
                queryStudentList();
                break;
            case R.id.btn_update_student_one:
                updateStudentOne();
                break;
            case R.id.btn_del_student_one:
                delStudentOne();
                break;
        }
    }

    private void delStudentOne() {
        new StudentDao(this).deleteById(97);
    }

    private void updateStudentOne() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", 105);
        new StudentDao(this).updateOne(contentValues, 5);
    }

    private void queryStudent() {
        List<Student> studentList = new StudentDao(this).listByArgs("姓名");
        for (int i = 0; i < studentList.size(); i++) {
            Log.d("TAG", studentList.get(i).getName());
            Log.d("TAG", "学号:" + studentList.get(i).getNumber());
        }
    }

    private void queryStudentList() {
        List<Student> studentList = new StudentDao(this).queryList(false, "姓名");
        for (int i = 0; i < studentList.size(); i++) {
            Log.d("TAG", "学号:" + studentList.get(i).getNumber());
        }
    }

    private void createStudent() {
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setName("姓名");
            student.setNumber(i);
            new StudentDao(this).add(student);
        }
    }

    /**
     * 创建单独的user,主键唯一
     * 未与article关联
     */
    private void createUser() {
        User user = new User();
        user.setName("fanyafeng");
        new UserDao(this).add(user);
    }

    private void createUserArticle() {
        User user = new User();
        user.setName("表关联");
        new UserDao(this).add(user);
        Article article = new Article();
        article.setTitle("表关联头");
        article.setUser(user);
        new ArticleDao(this).add(article);
    }

    private void getArticleByid() {
        Article article = new ArticleDao(this).get(1);
        Log.d("TAG", article.getTitle());
    }

    private void getUserByid(int id) {
        User user = new UserDao(this).get(id);
        Log.d("TAG", user.getId() + "|" + user.getName());
    }

    private void getArticleByArticleId() {
//        需要先判空
        Article article = new ArticleDao(this).getArticleWithUser(1);
        Log.d("TAG", article.getId() + "," + article.getTitle());
    }

    private void getArticleByUserId() {
        List<Article> articleList = new ArticleDao(this).listByUserId(1);
        Log.d("TAG", articleList.get(0).getId() + "|" + articleList.get(0).getTitle() + "|" + articleList.get(0).getUser());
        Log.d("TAG", articleList.get(0).getUser().getId() + "|" + articleList.get(0).getUser().getName());
        getUserByid(articleList.get(0).getUser().getId());

    }
}
