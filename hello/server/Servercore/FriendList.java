package hello.server.Servercore;

import hello.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class FriendList {

	private static List<Member>  friendList = new ArrayList<Member>();
	
	

	
	
	public static Member getFriendList(int index){
		if(friendList.size()>0){
			return friendList.get(index);
		}
		return null;
	}
	public static void addFriendList(Member member){
		if(!friendList.contains(member)){
			friendList.add(member);
		}
	}
	
	public static List<Member> getFriendListAll(){
		return friendList;
	}
}
