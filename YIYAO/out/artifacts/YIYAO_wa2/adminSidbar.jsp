<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  	<!-- Sidebar -->
    <div class="sidebar">
        <div class="sidebar-dropdown"><a href="#">导航</a></div>

        <!--- Sidebar navigation -->
        <!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
        <ul id="nav">
          <!-- Main menu with font awesome icon -->
          <li><a href="indexServlet" class="open"><i class="icon-home"></i> 首页</a>
           
          </li>
          <li class="has_sub"><a href="#"><i class="icon-list-alt"></i> 系统信息管理  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
              <li><a href="ManageClazzesServlet">药品信息管理</a></li>
              <li><a href="ManageStudentsServlet">仓库信息管理</a></li>
              <li><a href="ManageTeachersServlet">订单信息管理</a></li>
              <li><a href="ManageCoursesServlet">员工信息管理</a></li>
            </ul>
          </li>  
          
        </ul>
    </div>

    <!-- Sidebar ends -->
