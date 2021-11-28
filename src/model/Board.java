/*Kaio Abreu de Freitas - 1913003, Leonardo Trote Martins - 1620572, Ricardo Matheus de Oliveira Amaral - 1621644*/
package model;

import java.util.*;

class Board {
	
	//lista com as casas que contem metas
	private Goal lGoal[];
	private Map<String, String> hashmap = new HashMap<String, String>();

	
	Board(){
		lGoal= new Goal[12];
		lGoal[0]=new Goal(0,2,4,0);
		lGoal[1]=new Goal(1,2,4,1);
		lGoal[2]=new Goal(2,4,5,0);
		lGoal[3]=new Goal(3,4,5,1);
		lGoal[4]=new Goal(4,5,6,0);
		lGoal[5]=new Goal(5,5,6,1);
		lGoal[6]=new Goal(6,8,4,0);
		lGoal[7]=new Goal(7,8,4,1);
		lGoal[8]=new Goal(8,10,5,0);
		lGoal[9]=new Goal(9,10,5,1);
		lGoal[10]=new Goal(10,11,6,0);
		lGoal[11]=new Goal(11,11,6,1);
		hashmap.put("(0,0,0)","208,368");
		hashmap.put("(1,1,0)","251,356");
		hashmap.put("(2,1,0)","273,352");
		hashmap.put("(3,1,0)","296,345");
		hashmap.put("(4,1,0)","318,339");
		hashmap.put("(5,1,0)","341,333");
		hashmap.put("(6,1,0)","363,326");
		hashmap.put("(1,2,0)","243,333");
		hashmap.put("(2,2,0)","259,314");
		hashmap.put("(3,2,0)","278,296");
		hashmap.put("(4,2,0)","297,278");
		hashmap.put("(5,2,0)","314,258");
		hashmap.put("(6,2,0)","333,240");
		hashmap.put("(1,3,0)","221,321");
		hashmap.put("(2,3,0)","226,296");
		hashmap.put("(3,3,0)","233,271");
		hashmap.put("(4,3,0)","240,245");
		hashmap.put("(5,3,0)","246,219");
		hashmap.put("(6,3,0)","253,194");
		hashmap.put("(1,4,0)","197,322");
		hashmap.put("(2,4,0)","188,297");
		hashmap.put("(3,4,0)","181,271");
		hashmap.put("(4,4,0)","175,245");
		hashmap.put("(5,4,0)","168,220");
		hashmap.put("(6,4,0)","159,195");
		hashmap.put("(1,5,0)","176,334");
		hashmap.put("(2,5,0)","155,316");
		hashmap.put("(3,5,0)","136,298");
		hashmap.put("(4,5,0)","117,280");
		hashmap.put("(5,5,0)","101,260");
		hashmap.put("(6,5,0)","82,241");
		hashmap.put("(1,6,0)","166,356");
		hashmap.put("(2,6,0)","144,352");
		hashmap.put("(3,6,0)","121,346");
		hashmap.put("(4,6,0)","100,340");
		hashmap.put("(5,6,0)","77,333");
		hashmap.put("(6,6,0)","52,329");
		hashmap.put("(1,7,0)","165,377");
		hashmap.put("(2,7,0)","144,384");
		hashmap.put("(3,7,0)","120,390");
		hashmap.put("(4,7,0)","100,397");
		hashmap.put("(5,7,0)","76,401");
		hashmap.put("(6,7,0)","53,410");
		hashmap.put("(1,8,0)","174,401");
		hashmap.put("(2,8,0)","155,421");
		hashmap.put("(3,8,0)","138,440");
		hashmap.put("(4,8,0)","119,459");
		hashmap.put("(5,8,0)","102,478");
		hashmap.put("(6,8,0)","81,497");
		hashmap.put("(1,9,0)","195,414");
		hashmap.put("(2,9,0)","190,440");
		hashmap.put("(3,9,0)","182,466");
		hashmap.put("(4,9,0)","176,490");
		hashmap.put("(5,9,0)","168,519");
		hashmap.put("(6,9,0)","163,539");
		hashmap.put("(1,10,0)","221,415");
		hashmap.put("(2,10,0)","227,439");
		hashmap.put("(3,10,0)","236,466");
		hashmap.put("(4,10,0)","241,491");
		hashmap.put("(5,10,0)","246,516");
		hashmap.put("(6,10,0)","253,540");
		hashmap.put("(1,11,0)","244,402");
		hashmap.put("(2,11,0)","259,421");
		hashmap.put("(3,11,0)","279,440");
		hashmap.put("(4,11,0)","296,459");
		hashmap.put("(5,11,0)","314,477");
		hashmap.put("(6,11,0)","332,495");
		hashmap.put("(1,12,0)","252,380");
		hashmap.put("(2,12,0)","273,385");
		hashmap.put("(3,12,0)","296,391");
		hashmap.put("(4,12,0)","318,396");
		hashmap.put("(5,12,0)","339,401");
		hashmap.put("(6,12,0)","363,403");
		hashmap.put("(0,0,1)","540,369");
		hashmap.put("(1,1,1)","497,355");
		hashmap.put("(2,1,1)","475,349");
		hashmap.put("(3,1,1)","453,344");
		hashmap.put("(4,1,1)","430,339");
		hashmap.put("(5,1,1)","407,332");
		hashmap.put("(6,1,1)","386,327");
		hashmap.put("(1,2,1)","505,330");
		hashmap.put("(2,2,1)","487,315");
		hashmap.put("(3,2,1)","470,295");
		hashmap.put("(4,2,1)","449,278");
		hashmap.put("(5,2,1)","432,258");
		hashmap.put("(6,2,1)","414,241");
		hashmap.put("(1,3,1)","528,318");
		hashmap.put("(2,3,1)","522,295");
		hashmap.put("(3,3,1)","515,268");
		hashmap.put("(4,3,1)","508,245");
		hashmap.put("(5,3,1)","502,218");
		hashmap.put("(6,3,1)","496,195");
		hashmap.put("(1,4,1)","552,319");
		hashmap.put("(2,4,1)","557,296");
		hashmap.put("(3,4,1)","565,267");
		hashmap.put("(4,4,1)","571,243");
		hashmap.put("(5,4,1)","576,217");
		hashmap.put("(6,4,1)","585,195");
		hashmap.put("(1,5,1)","574,330");
		hashmap.put("(2,5,1)","592,313");
		hashmap.put("(3,5,1)","610,294");
		hashmap.put("(4,5,1)","628,276");
		hashmap.put("(5,5,1)","647,256");
		hashmap.put("(6,5,1)","667,238");
		hashmap.put("(1,6,1)","584,355");
		hashmap.put("(2,6,1)","604,348");
		hashmap.put("(3,6,1)","625,345");
		hashmap.put("(4,6,1)","649,337");
		hashmap.put("(5,6,1)","671,332");
		hashmap.put("(6,6,1)","694,323");
		hashmap.put("(1,7,1)","582,379");
		hashmap.put("(2,7,1)","604,384");
		hashmap.put("(3,7,1)","625,388");
		hashmap.put("(4,7,1)","648,392");
		hashmap.put("(5,7,1)","671,402");
		hashmap.put("(6,7,1)","694,403");
		hashmap.put("(1,8,1)","573,400");
		hashmap.put("(2,8,1)","593,421");
		hashmap.put("(3,8,1)","611,436");
		hashmap.put("(4,8,1)","628,454");
		hashmap.put("(5,8,1)","647,476");
		hashmap.put("(6,8,1)","665,494");
		hashmap.put("(1,9,1)","552,413");
		hashmap.put("(2,9,1)","558,438");
		hashmap.put("(3,9,1)","565,463");
		hashmap.put("(4,9,1)","572,489");
		hashmap.put("(5,9,1)","580,513");
		hashmap.put("(6,9,1)","587,537");
		hashmap.put("(1,10,1)","529,412");
		hashmap.put("(2,10,1)","522,438");
		hashmap.put("(3,10,1)","515,463");
		hashmap.put("(4,10,1)","510,488");
		hashmap.put("(5,10,1)","504,513");
		hashmap.put("(6,10,1)","495,539");
		hashmap.put("(1,11,1)","506,400");
		hashmap.put("(2,11,1)","487,421");
		hashmap.put("(3,11,1)","469,437");
		hashmap.put("(4,11,1)","451,457");
		hashmap.put("(5,11,1)","435,477");
		hashmap.put("(6,11,1)","416,494");
		hashmap.put("(1,12,1)","497,378");
		hashmap.put("(2,12,1)","474,385");
		hashmap.put("(3,12,1)","453,391");
		hashmap.put("(4,12,1)","431,395");
		hashmap.put("(5,12,1)","409,403");
		hashmap.put("(6,12,1)","387,410");

		
	}
	
	//retorna a lista de metas
	public Goal[] get_lGoal() {
		return lGoal;
	}
	
	public Map<String, String> get_hashmap() {
		return hashmap;
	}
	//remove meta da lista de metas
	public void remove_goal(int x, int y, int pole) {
		Goal newList[]= new Goal[lGoal.length-1];
		for (int i=0,j=0;i<lGoal.length;i++) {
			if(lGoal[i].get_x()!=x || lGoal[i].get_y()!=y || lGoal[i].get_pole()!=pole) {
			newList[j]=lGoal[i];
			j++;
			}
		}
		lGoal=newList;
	}
}