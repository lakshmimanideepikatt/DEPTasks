interface University
{
    default void universityInfo()
    {
        System.out.println("University Name : Something");
        System.out.println("Number of departments : 100");
    }
}
interface CollegeData extends University
{
    public void collegeDetail(); 
    default void studentData() 
    {
        System.out.println("courses of Student : MTECH, MBA, BCA");
    }
}
interface HostelData extends University
{
    public void hostelDetail(); 
    default void studentData()
    {
        System.out.println("Student selected on based : Percentage, Financial condition");
    }
}
public class StudentRecord implements CollegeData, HostelData 
{ 
    public void hostelDetail() 
    {
        System.out.println("Hostel Name : Yamuna");
        System.out.println("Hostel location : Hyd");
    }
    public void collegeDetail() 
    {
        System.out.println("College Name : VIYT");
        System.out.println("College Grade : A");
        System.out.println("University of College : Jntuk");
    }
   
}