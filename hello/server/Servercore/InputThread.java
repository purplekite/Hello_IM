package hello.server.Servercore;

import hello.client.clientcore.FriendList;
import hello.entity.Member;
import hello.entity.TranObject;
import hello.entity.TranObjectType;
import hello.server.dao.手机dao;
import hello.server.dao.改密码dao;
import hello.server.dao.注册dao;
import hello.server.dao.登录dao;
import hello.server.ui.ServerFrame;
import hello.server.util.JDBCUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;




public class InputThread extends Thread{
	private Socket socket ;
	private OutputThread out;
	private OutputThreadMap map;
	private ObjectInputStream ois;
	private boolean isStart = true;
	
	public InputThread(Socket socket, OutputThread out, OutputThreadMap map){
		this.socket = socket;
		this.out = out;
		this.map = map;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(isStart){
				readMessage();
			}
			if(ois != null){
				ois.close();
			}
			if(socket != null){
				socket.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	public void readMessage() throws Exception{
		TranObject readObject = (TranObject) ois.readObject();
		TranObjectType tType = readObject.getType();
		switch(tType){
		case REGISTER:
			Member memberReg = (Member)readObject.getObject();
			TranObject objReg = new TranObject();
			objReg.setType(TranObjectType.REGISTER);
			注册dao daoRegister = new 注册dao();
			手机dao daoPhone = new 手机dao();
			boolean yz = daoPhone.yanzheng(memberReg.getPhone());
			String ti1 = yz ? "该手机号已注册" : "";
			if (yz) {
				objReg.setCmd("rbid");
				objReg.setObject("该手机号已注册");
			} else {
				boolean saveFlag = daoRegister.inSert(memberReg.getName(), memberReg.getLoginPwd(),
						memberReg.getSex(), memberReg.getAge(), memberReg.getPhone());
				objReg.setCmd("txts");
				if (saveFlag) {
					objReg.setObject("注册成功");
				} else {
					objReg.setObject("注册失败");
				}
			}
			out.setMessage(objReg);

		
			break;
		case LOGIN:
			Member member = (Member) readObject.getObject();
			TranObject sendObject = new TranObject();
			sendObject.setType(TranObjectType.LOGIN);
			登录dao dao = new 登录dao();
			String pwd = dao.Select(member.getPhone());
			boolean ph = dao.phone(member.getPhone());
			if (ph) {
				
				if (pwd.equals(member.getLoginPwd())) {
					sendObject.setCmd("true");
					sendObject.setToUser(member.getMemberId());
					Member memLogin = JDBCUtils.queryForObject("select * from member where phone = ?", Member.class, member.getPhone());
					memLogin.setLoginPwd("");
					sendObject.setObject(memLogin);
					out.setMessage(sendObject);
//					sleep(50);
					String logMessage = memLogin.getName()+"登录成功。\n";
					ServerFrame serverFrame = (ServerFrame)ThreadMap.getThreadMap("serverFrame");
					serverFrame.showMessage(logMessage);
					if(isExistMember(memLogin)){
						FriendList.addFriendList(memLogin);
					}
					map.add(memLogin.getMemberId(), out);
					
					//登录成功处理事件,通知好友上线
					TranObject loginmessage = new TranObject();
					loginmessage.setType(TranObjectType.FRIENDLOGIN);
					loginmessage.setObject(memLogin);
					List<OutputThread> list = map.getAll();
					for (OutputThread outputThread : list) {
						if(!out.getSocket().equals(outputThread.getSocket())){
							outputThread.setMessage(loginmessage);
//							sleep(50);
						}
					}
				} else {
					sendObject.setCmd("rbpwd");
					sendObject.setObject("密码错误");
					out.setMessage(sendObject);
				}
			} else {
				sendObject.setCmd("rbid");
				sendObject.setObject("账号不存在");	
				out.setMessage(sendObject);
			}
			break;
		case FORGETPWD:
			Member memForgetPwd = (Member)readObject.getObject();
			TranObject ForgetPwdObject = new TranObject();
			ForgetPwdObject.setType(TranObjectType.FORGETPWD);
			改密码dao daoForget = new 改密码dao();
			boolean gm = daoForget.upDate(memForgetPwd.getLoginPwd(), memForgetPwd.getPhone());
			ForgetPwdObject.setCmd("jxg");
			if (gm) {
				ForgetPwdObject.setObject("修改成功");
			} else {
				ForgetPwdObject.setObject("修改失败");
			}
			out.setMessage(ForgetPwdObject);
			break;
		case GROUPMESSAGE:
			Member memGroup = readObject.getFromMember();
//			if(map.getById(readObject.getFromUser()) == null){
//				map.add(memGroup.getMemberId(), out);
//			}
			ArrayList<OutputThread> list = (ArrayList<OutputThread>) map.getAll();
			for (OutputThread outputThread : list) {
				if(!outputThread.equals(out)){
					outputThread.setMessage(readObject);
				}
			}
			break;
		case MESSAGE:
			OutputThread output = map.getById(readObject.getToUser());
			output.setMessage(readObject);
			break;
		case REFRESH:
			TranObject getOnlineFriend = new TranObject();
			getOnlineFriend.setType(TranObjectType.REFRESH);
			getOnlineFriend.setObject(FriendList.getFriendListAll());
			out.setMessage(getOnlineFriend);
			break;
		case FRIENDLOGOUT:
			int idLogout = readObject.getFromUser();
			FriendList.removeFriendList(idLogout);
			ArrayList<OutputThread> outList = (ArrayList<OutputThread>) map.getAll();
			for (OutputThread outputThread : outList) {
				outputThread.setMessage(readObject);
				if(outputThread.equals(out)){
					map.remove(idLogout);
					sleep(10);
					out.setStart(false);
				}
			}
			isStart = false;			
			break;
		default:
			break;
		}
	}
	public boolean isExistMember(Member member){
		for (int i = 0; i < FriendList.getSize(); i++) {
			if(FriendList.getFriendList(i).getMemberId()==(member.getMemberId())){
				return false;
			}
		}
		return true;
	}
	
	
}
