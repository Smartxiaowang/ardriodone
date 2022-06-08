package entity;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import db.DBOper;

public class CommandDao extends DBOper{
//	��ȡ��ǰ��Ӱ����������
	public List<Command> getCommand(int mId){
		List<Command> lst = new ArrayList<Command>();
		String sql2 = "select * from comment where mId="+mId+" order by cTime desc";
		try {
			ResultSet rs2 = this.executeQuery(sql2,new String[] {});
			while(rs2.next()){
				int uId=rs2.getInt("uId");
				String sql3 = "select * from user where uId ="+uId;
				ResultSet rs3 = this.executeQuery(sql3, new String[] {});
				if(rs3.next()){	
				}
				Command command = new Command();
				command.setcId(rs2.getInt("cId"));
				command.setuId(rs2.getInt("uId"));
				command.setmId(rs2.getInt("mId"));
				command.setcWord(rs2.getString("cWord"));
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String cTime=dateFormat.format(rs2.getDate("cTime"));
				command.setcTime(cTime);
				command.setuName(rs3.getString("uName"));
				lst.add(command);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}
//	�������
	public boolean add(int uId,int mId,String cWord,String cTime){
//		cId�Զ�����
		String sql = "insert into comment(uId,mId,cWord,cTime) values("+uId+","+mId+",?,?)";
		int rs=this.executeUpdate(sql, new String[] {cWord,cTime});
		boolean r=false;
		if(rs>0){
			r=true;
		}
		return r;
	}
	
//	ɾ������
	public boolean delete(String cId){
		String sql = "delete from comment where cId = "+cId;
		int rs = this.executeUpdate(sql, new String[] {});
		boolean r=false;
		if(rs>0){
			r=true;
		}
		return r;
	}
	
	
	
	
	
	
	
	
	
	
}
