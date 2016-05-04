package com.fanyafeng.orlitedemo.db;

import android.content.ContentValues;
import android.content.Context;

import com.fanyafeng.orlitedemo.bean.Student;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 365rili on 16/5/3.
 */
public class StudentDao {
    private Context context;
    private Dao<Student, Integer> studentIntegerDao;
    private DatabaseHelper databaseHelper;

    public StudentDao(Context context) {
        this.context = context;
        try {
            databaseHelper = DatabaseHelper.getHelper(context);
            studentIntegerDao = databaseHelper.getDao(Student.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Student student) {
        try {
            studentIntegerDao.create(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student get(int id) {
        Student student = new Student();
        try {
            student = studentIntegerDao.queryForId(id);
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    /**
     * 模糊查找
     *
     * @param name
     * @return
     */
    public List<Student> listByArgs(String name) {
        List<Student> studentList = new ArrayList<>();
        try {
            return studentIntegerDao.queryBuilder().where().like("name", name).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    /**
     * isUp是否是升序
     * name模糊查询传参
     *
     * @param isUp
     * @param name
     * @return
     */
    public List<Student> queryList(boolean isUp, String name) {
        List<Student> studentList = new ArrayList<>();

        try {
            studentList = studentIntegerDao.queryBuilder().orderBy("number", isUp).where().like("name", name).query();
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void updateOne(ContentValues contentValues, int number) {
        UpdateBuilder<Student, Integer> updateBuilder = studentIntegerDao.updateBuilder();
        try {
            updateBuilder.where().eq("number", 5);
            Iterator iterator = contentValues.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                int value = (int) contentValues.get(key);
                updateBuilder.updateColumnValue(key, value);
            }
            updateBuilder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int number) {
        DeleteBuilder deleteBuilder = studentIntegerDao.deleteBuilder();
        try {
            deleteBuilder.where().eq("number", number);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
