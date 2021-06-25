 class Circle {

   private double radius;
   private String color;
   public Circle() {
      this.radius = 1.0;
      this.color = "red";
   }
   public Circle(double radius) {
      this.radius = radius;
      this.color = "red";
   }
   public Circle(double radius, String color) {
      this.radius = radius;
      this.color = color;
   }
   public double getRadius() {
      return this.radius;
   }
   public String getColor() {
      return this.color;
   }
   public void setRadius(double radius) {
      this.radius = radius;
   }
   public void setColor(String color) {
      this.color = color;
   }
   public String toString() {
      return "Circle[radius=" + radius + ",color=" + color + "]";
   }
   public double getArea() {
      return radius * radius * Math.PI;
   }
}
class Sphere extends Circle{
    private double volume;
    public Sphere(){
        super();
    }
    public Sphere(double radius){
        super(radius);
    }
    public Sphere(double radius,String color){
        super(radius,color);
    }
    public double getVolume(){
        this.volume=super.getArea()*super.getRadius();
    }
}