//data for chat room
public class GameData
{

    //dont need to see old messages, just the new ones and the people who are in the chat room
   ArrayList<String> names = new ArrayList<String>();

    public ArrayList<String> getNames(){
        return names;
    }

    public void addNames(String user)
    {
        names.add(user);
    }


}
