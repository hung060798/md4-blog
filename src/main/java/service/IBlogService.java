package service;

import model.Blog;

import java.util.ArrayList;

public interface IBlogService {
    Blog save(Blog blog);

    ArrayList<Blog> fillAll();
    void delete(Blog blog);
    void edit(Blog blog);
}
