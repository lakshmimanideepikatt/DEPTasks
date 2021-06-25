class Bird {
  public void eat(){}
}
class FlightBird extends Bird{
     public void fly(){}
}
class NonFlightBird extends Bird{

}
class Crow extends FlightBird {
    public void eat(){
        //code
        System.out.println("crow eats");
    }
    public void fly(){
        System.out.println("Crow is flying..");
    }
}
class Ostrich extends NonFlightBird{
    public void eat(){
        System.out.println("Ostrich is eating")
    }
}
  
public BirdTest{
  public static void main(String[] args){
    List<Bird> birdList = new ArrayList<Bird>();
    birdList.add(new Bird());
    birdList.add(new Crow());
    birdList.add(new Ostrich());
    letTheBirdsFly ( birdList );
  }
  static void letTheBirdsFly ( List<Bird> birdList ){
    for ( Bird b : birdList ) {
      if(b instanceof FlightBird)
        b.fly();
    }
  }
}