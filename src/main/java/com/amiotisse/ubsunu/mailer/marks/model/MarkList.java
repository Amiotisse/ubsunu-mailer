package com.amiotisse.ubsunu.mailer.marks.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author himna
 * @since 5/6/2017.
 */
public class MarkList {

    private String title;

    private String ownerUserId;
    private List<Mark> list;

    public MarkList(String title, String ownerUserId, List<Mark> list) {
        this.title = title;
        this.ownerUserId = ownerUserId;
        this.list = list;
    }
    public MarkList(){
        this.list= new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public List<Mark> getList() {
        return list;
    }

    public void setList(List<Mark> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MarkList{" +
                "title='" + title + '\'' +
                ", ownerUserId='" + ownerUserId + '\'' +
                ", list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarkList markList = (MarkList) o;

        if (title != null ? !title.equals(markList.title) : markList.title != null) return false;
        if (ownerUserId != null ? !ownerUserId.equals(markList.ownerUserId) : markList.ownerUserId != null)
            return false;
        return list != null ? list.equals(markList.list) : markList.list == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (ownerUserId != null ? ownerUserId.hashCode() : 0);
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }
}
