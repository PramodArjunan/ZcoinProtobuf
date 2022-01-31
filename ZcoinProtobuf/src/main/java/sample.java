import com.google.protobuf.InvalidProtocolBufferException;

import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

public class sample {

	public static void main(String[] args) throws InvalidProtocolBufferException {
	
		CurrentUser user=new CurrentUser(); 
		UserDetails.Builder a=UserDetails.newBuilder();
		a.setEmail("pramod@gmail.com");
		a.build();
		
		//user.setSerialize(a.build().toByteArray());
		
	//	UserDetails q=UserDetails.parseFrom(user.getSerialize());
		
	//	UserDetails.Builder qq=q.toBuilder();
		
		
	//	qq.setPassword("Pramod");
	//	System.out.println(qq);
		
	///	UserDetails q1=UserDetails.parseFrom(user.getSerialize());
	//	System.out.println(q1);
	}

}
