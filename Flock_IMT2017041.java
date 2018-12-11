package sample;

import flockbase.*;
import flockbase.Flock;
import flockbase.Position;
import flockbase.Bird;
import java.util.ArrayList;

public class Flock_IMT2017041 extends flockbase.Flock
{
  private ArrayList<Bird> birds_in_flock = new ArrayList<Bird>();
  private Bird leader = null;
  
  public Flock_IMT2017041() 
  {
    //default constructor
    super();
  }
  
  public void addBird(Bird bird)
  {
    birds_in_flock.add(bird);
    bird.setFlock(this);
  }
  
  public void setLeader(Bird _leader)
  {
    if (leader != null) 
    {
      leader.retireLead();
    }
    else
    {
      leader = _leader;
      leader.becomeLeader();
    }
    
  }
  
  public ArrayList<Bird> getBirds()
  {
    return birds_in_flock;
  }
  

  public Bird getLeader()
  {
    return leader;
  }
  

  // public Flock split(int pos)
  // {
  //   return null;
  // }
  public Flock split(int n)
  {
    Bird bird_at_n = birds_in_flock.get(n);
    bird_at_n.becomeLeader();
    birds_in_flock.remove(n);

    Flock split_flock = new Flock_IMT2017041();
    split_flock.addBird(bird_at_n);
    split_flock.setLeader(bird_at_n);

    for(int i=0; i<n;i++)
    {
      split_flock.addBird(birds_in_flock.get(i));
    }

    for(int i= 0;i<n-1;i++) 
    {
      birds_in_flock.remove(i);
    }
    return split_flock;
  }

  
  public void joinFlock(Flock f)
  {
    getLeader().retireLead(); // first make the current leader retire 
    for(Bird b: getBirds()) // loop to add the birds to the current flock
    {
      f.addBird(b);
    }
  }
}
