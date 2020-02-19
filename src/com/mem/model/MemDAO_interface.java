package com.mem.model;

import java.util.*;

public interface MemDAO_interface {
    public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void delete(String memNo);
    public MemVO findByPrimaryKey(String memNo);
    public List<MemVO> getAll();
    public MemVO getEmail(String email);
//    public void justForMemPhoto(String memNo, byte[] memPhoto);
//    public boolean searchId(String idNo);
//    public MemVO getEmailConfirm(String e_mail);
    public String getUserCode(MemVO memVO);
    public MemVO confirmCode(String code);
    public void updatepoints(MemVO memVO);
    
}
