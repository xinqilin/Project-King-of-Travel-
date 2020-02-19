package com.Android.mem.model;


public interface MemDAO_interface {
    public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public Boolean isIdRegistered(String idNo);
    public MemVO findByEmail(String email);
    public String findTripCountByMemNo(String memNo);
    public String findArticleCountByMemNo(String memNo);
}
