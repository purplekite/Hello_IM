package hello.Servercore;

import hello.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class FriendList {

	private static List<Member>  friendList = new ArrayList<Member>();
	
	
	public static Member getFriednList(int index){

		return friendList.get(index);
	}
	public static void addFriendList(Member member){
		friendList.add(member);
	}
	
	public static List<Member> getFriendListAll(){
		return friendList;
	}
}