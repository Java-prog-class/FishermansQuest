FishermansQuest
===============

Text adventure game

This is the repository for **group 2's text adventure game.** You can all edit the same master branch, or even better, make your own branch, experiment, then merge them in.

  
  
  
    public void playSound(String soundName)
    {
      try 
      {
       AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
       Clip clip = AudioSystem.getClip();
       clip.open(audioInputStream);
       clip.start();
      }
      catch(Exception ex)
      {
        System.out.println("Error with playing sound.");
        ex.printStackTrace( );
      }
    }
