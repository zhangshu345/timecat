package com.time.cat.util;

import com.time.cat.NetworkSystem.ConstantURL;
import com.time.cat.ThemeSystem.ThemeManager;
import com.time.cat.mvp.model.APImodel.User;
import com.time.cat.mvp.model.DBmodel.DBNote;
import com.time.cat.mvp.model.DBmodel.DBTask;
import com.time.cat.mvp.model.DBmodel.DBUser;
import com.time.cat.mvp.model.Note;
import com.time.cat.mvp.model.Task;
import com.time.cat.util.source.AvatarManager;

/**
 * @author dlink
 * @date 2018/2/6
 * @discription 实现本地数据库model和网络请求model的转化
 */
public class ModelUtil {
    public static DBUser toDBUser(User u) {
        DBUser dbUser = new DBUser();
        dbUser.setEmail(u.getEmail());
        dbUser.setName(u.getUsername());
        dbUser.setAvatar(AvatarManager.AVATAR_4);
        dbUser.setColor(ThemeManager.CARD_THEME_0);
        dbUser.setDefault(true);
        dbUser.setPlans(u.getPlans());
        dbUser.setTags(u.getTags());
        dbUser.setTasks(u.getTasks());
//        dbUser.setPassword(u.getPassword());
        return dbUser;
    }

    public static DBUser toActiveDBUser(DBUser activeDBUser, User u) {
        activeDBUser.setEmail(u.getEmail());
        activeDBUser.setName(u.getUsername());
        activeDBUser.setPlans(u.getPlans());
        activeDBUser.setTags(u.getTags());
        activeDBUser.setTasks(u.getTasks());
//        dbUser.setPassword(u.getPassword());
        return activeDBUser;
    }

    public static User toAPIUser(DBUser dbUser) {
        User user = new User();
        user.setEmail(dbUser.getEmail());
//        user.setId(dbUser.id());
        user.setUsername(dbUser.name());
        user.setPassword(dbUser.getPassword());
        return user;
    }

    public static DBTask toDBTask(Task task) {
        DBTask dbTask = new DBTask();
        dbTask.setContent(task.getContent());
        dbTask.setCreated_datetime(task.getCreated_datetime());
        dbTask.setFinished_datetime(task.getFinished_datetime());
        dbTask.setBegin_datetime(task.getBegin_datetime());
        dbTask.setEnd_datetime(task.getEnd_datetime());
        dbTask.setIs_all_day(task.getIs_all_day());
        dbTask.setIsFinish(task.getIsFinish());
        dbTask.setUrl(task.getUrl());
        dbTask.setTitle(task.getTitle());
        dbTask.setOwner(task.getOwner());
        dbTask.setLabel(task.getLabel());
        return dbTask;
    }

    public static DBNote toDBNote(Note note) {
        DBNote dbNote = new DBNote();
        dbNote.setContent(note.getContent());
        dbNote.setCreated_datetime(note.getCreated_datetime());
        dbNote.setUpdate_datetime(note.getUpdate_datetime());
        dbNote.setUrl(note.getUrl());
        dbNote.setTitle(note.getTitle());
        dbNote.setOwner(note.getOwner());
        return dbNote;
    }

    public static String getOwnerUrl(User u) {
        return ConstantURL.BASE_URL_USERS + u.getEmail() + "/";
    }

    public static String getOwnerUrl(DBUser u) {
        return ConstantURL.BASE_URL_USERS + u.getEmail() + "/";
    }
}
