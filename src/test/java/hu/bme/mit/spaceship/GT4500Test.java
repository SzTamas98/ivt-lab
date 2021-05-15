package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore primaryMock;
  private TorpedoStore secondaryMock;
  private GT4500 ship;

  @BeforeEach
  public void init(){

    primaryMock=mock(TorpedoStore.class);
    secondaryMock=mock(TorpedoStore.class);

    this.ship = new GT4500(primaryMock,secondaryMock);
    
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true,result);
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
  }


  @Test
  public void fireTorpedo_SINGLE_First_Used(){
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    
    ship.setWasPrimaryFired(true);
    boolean result=ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
    
  }

  @Test
  public void fireTorpedo_SINGLE_First_Empty(){
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);


    boolean result=ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
    
  }

  @Test
  public void fireTorpedo_SINGLE_First_Fails(){
    when(primaryMock.fire(1)).thenReturn(false);
    


    boolean result=ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, result);
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
    
  }
  
  @Test
  public void fireTorpedo_SINGLE_First_Used_Second_Empty(){
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);
    

    ship.setWasPrimaryFired(true);
    boolean result=ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true, result);
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
    
  }


  @Test
  public void fireTorpedo_ALL_One_Falis(){
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(false);
    
    boolean result=ship.fireTorpedo(FiringMode.ALL);

    assertEquals(false, result);
    verify(primaryMock, times(1)).fire(1);
    verify(secondaryMock, times(1)).fire(1);
    
  }


  @Test
  public void fireTorpedo_SINGLE_Both_Empty(){
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);
    
    
    boolean result=ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, result);
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
    
  }

  @Test
  public void fireTorpedo_SINGLE_Both_Empty_First_Used(){
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);
    
    ship.setWasPrimaryFired(true);
    boolean result=ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(false, result);
    verify(primaryMock, times(0)).fire(1);
    verify(secondaryMock, times(0)).fire(1);
    
  }

  


}
