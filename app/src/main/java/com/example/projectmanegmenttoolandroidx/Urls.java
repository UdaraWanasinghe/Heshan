package com.example.projectmanegmenttoolandroidx;

public class Urls {
    // host
    private final static String HOST = "http://192.168.43.195:3000/api/";

    // register
    final static String REGISTER_URL = HOST + "users/register";

    // login
    final static String LOGIN_URL = HOST + "auth/login";

    // projects
    final static String NEW_PROJECT_URL = HOST + "projects/newproject";
    public final static String GET_BY_EMAIL_URL = HOST + "projects/getByemail";
    final static String GET_CATEGORIES_URL = HOST + "projects/getCategories";
    final static String GET_PROJECT_TASKS_URL = HOST + "projects/getProjectTasks";
    final static String NEW_CATEGORY_URL = HOST + "projects/newcategory";
    final static String NEW_PROJECT_TASK_URL = HOST + "projects/newProjectTask";
    final static String PROJECT_ROLE_ASSIGN_URL = HOST + "projects/projectRoleAssign";
    final static String INVITE_COL_URL = HOST + "projects/inviteCol";
    final static String ACCEPT_INVITE_URL = HOST + "projects/acceptInvite";
    final static String GET_DOCS_URL = HOST + "projects/getDocs";
    final static String PROJECT_TASK_ASSIGN_URL = HOST + "projects/projectTaskAssign";

}
