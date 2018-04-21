public class Counter{
  private FcukBase base = new FcukBase();
  
  public int CountUsers(){
    return base.getNumberOfUsers();
  }
  
  public int CountCopies(){
    return base.getNumberOfDocs();
  }
}
