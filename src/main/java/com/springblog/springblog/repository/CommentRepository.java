package com.springblog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springblog.springblog.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {//因為id 是 long這邊寫long

}
