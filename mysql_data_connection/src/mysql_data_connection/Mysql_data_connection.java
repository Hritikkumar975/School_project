
package mysql_data_connection;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.*;
public class Mysql_data_connection {

     Connection con;
    Statement stmt;
    ResultSet rs;
    PreparedStatement pstmt;
    Scanner s1;
     String regid = null;
        String sname = null;
        String fname = null;
        String sdob = null;
        String rollno = null;
        
    Mysql_data_connection()throws SQLException{
       con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","void@1234");
       stmt=con.createStatement();
       s1=new Scanner(System.in);
      
    }
    public void student_data()throws SQLException{
//        Class.forName("com.mysql.cj.jdbc.Driver");
System.out.print("\tEnter Student roll no : ");
String Student_roll=s1.nextLine();
System.out.print("\tEnter Student name : ");
String Student_name=s1.nextLine();
Mysql_data_connection m=new Mysql_data_connection();
try {
            
       
    
       String query="select*from personal where sname='"+Student_name+"' && rollno='"+Student_roll+"';";
//       String query1="select*from personal;";
       rs=stmt.executeQuery(query);
    if(rs.next()){
         regid=rs.getString(1);
         sname=rs.getString(2);
         fname=rs.getString(3);
         sdob=rs.getString(4);
         rollno=rs.getString(5);
    }
    if(regid==null){
    System.out.println("\t\t\t\t\t\t\t!!!!!!!!!!!Not Student recodes found Please try again!!!!!!!!!!!!!!!!!!!!!!");
    }else{
        m.data(regid, sname, fname, sdob, rollno);
    }
    }
 catch (Exception e) {
     System.out.println(e.toString());
        }
m.option();
    }
    
    
    public void update_student()throws SQLException{
    System.out.print("\tEnter Student roll no : ");
String Student_roll=s1.nextLine();
System.out.print("\tEnter Student name : ");
String Student_name=s1.nextLine();
Mysql_data_connection ob=new Mysql_data_connection();
try {
       String query="select regid,rollno from personal where sname='"+Student_name+"' && rollno='"+Student_roll+"';";
//       String query1="select*from personal;";
       rs=stmt.executeQuery(query);
    if(rs.next()){
         regid=rs.getString(1);
         rollno=rs.getString(2);
      
    }
    if(regid==null){
    System.out.println("\t\t\t\t\t\t\t!!!!!!!!!!!Not Student recodes for updating please try again!!!!!!!!!!!!!!!!!!!!!!");
    ob.option();
    }else{
        System.out.println("\t\t\tYour recode found successfully\n");
        System.out.println("\n\tYour ID is : "+regid);
        System.out.println("\tYour roll no is : "+rollno);
        System.out.print("\tEnter your name : ");
        String student_name=s1.nextLine();
        System.out.print("\tEnter your father name : ");
        String student_fname=s1.nextLine();
         System.out.println("\tEnter your Date of birth to new recode");
          System.out.print("\tEnter your birth year :");
          String d_year=s1.nextLine();
          System.out.print("\tEnter your brith month : ");
          String d_month=s1.nextLine();
          System.out.print("\tEnter your brith date : ");
          String d_date=s1.nextLine();
        String query1="update personal set sname='"+student_name+"',fname='"+student_fname+"',dob='"+d_year+"/"+d_month+"/"+d_date+"' where regid='"+rs.getString(1)+"'";
         int a=stmt.executeUpdate(query1);
         System.out.println(a);
         if(a==1){
         System.out.println("\n\t\t\t\t\t---------------your data is update successfully---------");
         ob.data(regid, student_name, student_fname, sdob, rollno);
         }else{
         System.out.println("\t\t\t------------!!!!!!!!!!Something went wrong pleass try again-------------------");
         ob.option();
         }
    }
    }catch(Exception e){
    System.out.println(e.toString());
    }
    }
    public void remove_student()throws SQLException{
        Mysql_data_connection ob=new Mysql_data_connection();
        System.out.print("\t\t\tEnter student name to : ");
        String student_name=s1.nextLine();
        System.out.print("\t\t\tEnter student roll no : ");
        String Student_rollno=s1.nextLine();
        String query="Select * from personal where sname='"+student_name+"' && rollno='"+Student_rollno+"'";
        rs=stmt.executeQuery(query);
        if(rs.next()){
        System.out.print("\t\t\tYour confirm to delete student data to enter [y/n]");
        String confirm=s1.nextLine();
        if(confirm.equals("y") || confirm.equals("Y")){
        String query1="delete from personal where sname='"+student_name+"' && rollno='"+Student_rollno+"'";
        int a=stmt.executeUpdate(query1);
        if(a==1){
            System.out.println("\n\n\t\t\t\t\tyour student detials delete successfully");
            ob.option();
        }else{
        System.out.println("\t\t\tSomething went wrong");
        }
        }else{
        System.out.println("\t\t\tYour deleting processing is canceled Susseccfully");
        ob.option();
        }
        }else{
        System.out.println("\t\t\t\t!!!!!!!!!!!No data founding to delete student detials Please try again!!!!!!!!!");
        ob.option();
        }
    }
    public void teaher_data()throws SQLException{
         Mysql_data_connection ob=new Mysql_data_connection();
        System.out.println("Enter your teacher longin username");
        String user_name=s1.nextLine();
        System.out.println("Enter your login password");
        String psswd=s1.nextLine();
          String query="Select*from teacher_data;";
         rs=stmt.executeQuery(query);
       if(rs.next()){
          if(rs.getString(1).equals(user_name) && rs.getString(2).equals(psswd)){
            System.out.println("\t\t\t\t\t\t---------------Are you logined in teacher id------------------------");
            String query1="Select*from personal;";
             rs=stmt.executeQuery(query1);
             System.out.println("\n\t\tAnd see all student data");
             System.out.print("\t\tStudent ID\t\tStdent name\t\tStudent father name\t\tStudent date of brith\t\tStduent rollno");
             System.out.print("\n");
             System.out.println("\t\t--------------------------------------------------------------------------------------------------------------------------");          
             while(rs.next()){
         regid=rs.getString(1);
         sname=rs.getString(2);
         fname=rs.getString(3);
         sdob=rs.getString(4);
         rollno=rs.getString(5);
         
         System.out.print("\n");
         System.out.println("\t\t"+regid+"\t\t\t"+sname+"\t\t\t"+fname+"\t\t\t"+sdob+"\t\t\t"+rollno+"");
        
        }
    
    }else{
         System.out.println("\t\t\t\t\t\t\n\n!!!!!!!!!!!!Invalid enteries !!!!! Please try again!!!!!!");
        ob.option();
        }
         ob.option();      
       }
       
       
       
    }
    public void addnew()throws SQLException{
        Mysql_data_connection ob=new Mysql_data_connection();
        String query="select max(regid),max(rollno) from personal;";
        rs=stmt.executeQuery(query);
        int new_regid = 0;
        int new_rollno= 0;
        if(rs.next()){
        new_regid=Integer.parseInt(rs.getString(1))+1;
        new_rollno=Integer.parseInt(rs.getString(2))+1;
        }
          System.out.print("\tEnter your name to new recode : ");
          String sname=s1.nextLine();
          System.out.print("\tEnter your father name to new recode : ");
          String fname=s1.nextLine();
          System.out.println("\tEnter your Date of birth to new recode");
          System.out.print("\tEnter your birth year :");
          String d_year=s1.nextLine();
          System.out.print("\tEnter your brith month : ");
          String d_month=s1.nextLine();
          System.out.print("\tEnter your brith date : ");
          String d_date=s1.nextLine();
          String query1="insert into personal value('"+new_regid+"','"+sname+"','"+fname+"','"+d_year+"/"+d_month+"/"+d_date+"','"+new_rollno+"');";
          int a=stmt.executeUpdate(query1);
          if(a==1){
              System.out.println("\n\t\t\t\t-------- YOUR ROLL NO IS : "+new_rollno+" -------------------");
          System.out.println("\n\t\t\tYour recode successfully\nVeiw to select 1 option in menu");
          ob.option();
          }else{
          System.out.println("\t\tSomething went wrong");
          }
          
          
          
        }
    public void data(String id,String sname,String fname,String sdob,String rollno)throws SQLException{
        Mysql_data_connection ob=new Mysql_data_connection();
    System.out.println("\t\t\t\t\t\t\t\t\tStudent detials are founds correctly");
    System.out.println("\t\t\t\t\t\t\tStudents id is :"+id);
    System.out.println("\t\t\t\t\t\t\tStudents names is :"+sname);
    System.out.println("\t\t\t\t\t\t\tStudents father name is :"+fname);
    System.out.println("\t\t\t\t\t\t\tStudents date of birth :"+sdob);
    System.out.println("\t\t\t\t\t\t\tStudents Roll no  :"+rollno);
    ob.option();
    
    }
    public void option(){
        try{
        Mysql_data_connection my=new Mysql_data_connection();
        System.out.println("\n\n\n\n\n");
        System.out.println("\t\t\t\t\t\t\t--------------------Student Hritik project----------------------------\n\n");
    System.out.println("\t\t\t\t\t\t\tPress 1 to see Student detials");
    System.out.println("\t\t\t\t\t\t\tPress 2 to update student detials");
    System.out.println("\t\t\t\t\t\t\tPress 3 to permanetly remove student detials");
    System.out.println("\t\t\t\t\t\t\tPress 4 to login teacher to see all student detials");
    System.out.println("\t\t\t\t\t\t\tPress 5 to Add new data of student");
    System.out.println("\t\t\t\t\t\t\tPress 6 to exit program");
        System.out.println("\t\t\t\t\t\t\t--------------------Copyright \u00a9 HRvoid----------------------------\n\n");
   
    int o=s1.nextInt();
    switch(o){
        case 1: my.student_data();
        System.out.println("\n\n\n\n");
        break;
        case 2: my.update_student();
        System.out.println("\n\n\n\n");
        break;
        case 3: my.remove_student();
        System.out.println("\n\n\n\n");
        break;
        case 4: my.teaher_data();
        System.out.println("\n\n\n\n");
        break;
        case 5: my.addnew();
        System.out.println("\n\n\n\n");
        break;
        case 6: System.exit(0);
        default: System.out.println("Select valid values in options");
        my.option();
    }
        }catch(SQLException e){
        System.out.println(e);
        System.out.println("Something went wrong Please try again");
        
        }
        
    
    }
    public static void main(String[] args){
        
        try {
            Mysql_data_connection my1=new Mysql_data_connection();
        my1.option();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
