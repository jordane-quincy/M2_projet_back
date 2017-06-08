package org.istv.ske.core.utils;

import java.util.HashSet;
import java.util.Set;

public class StringUtils {

	public static final Set<String> WORDS_TO_DELETE = new HashSet<>();

	static {
		String words = "a,z,e,r,t,y,u,i,o,p,q,s,d,f,g,h,j,k,l,m,w,x,c,v,b,n,un,une,le,la,l’,les,',l,au,aux,du,des,mon,ma,mes,ton,ta,tes,son,sa,ses,notre,nos,votre,vos,leur,leurs,ce,cet,cette,ces,aucun,chaque,nul,plusieurs,quelques,certains,un,deux,trois,AA,AH,AI,AN,AS,AU,AY,BA,BE,BI,BU,CA,CE,CI,DA,DE,DO,DU,EH,EN,ES,ET,EU,EX,FA,FI,GO,HA,HE,HI,HO,IF,IL,IN,JE,KA,LA,LE,LI,LU,MA,ME,MI,MU,NA,NE,NI,NO,NU,OC,OH,OM,ON,OR,OS,OU,PI,PU,RA,RE,RI,RU,SA,SE,SI,SU,TA,TE,TO,TU,UD,UN,US,UT,VA,VE,VS,VU,WU,XI";
		for (String word : words.split(","))
			WORDS_TO_DELETE.add(word.toLowerCase());
	}

	public static String removeAccents(String src) {
		StringBuffer b = new StringBuffer();
		for (char c : src.toCharArray()) {
			b.append(getCharWithoutAccent(c));
		}
		return b.toString();
	}

	public static char getCharWithoutAccent(char in) {
		if (in == 'à' || in == 'â' || in == 'ä')
			return 'a';
		if (in == 'é' || in == 'è' || in == 'ê' || in == 'ë')
			return 'e';
		if (in == 'ï' || in == 'î')
			return 'i';
		if (in == 'ô' || in == 'ö')
			return 'o';
		if (in == 'ü' || in == 'û' || in == 'ù')
			return 'u';
		if (in == 'ÿ')
			return 'y';
		return in;
	}

	public static String join(Set<String> array) {
		StringBuffer b = new StringBuffer();
		boolean comma = false;
		for (String str : array) {
			if (comma)
				b.append(',');
			b.append(str);
			comma = true;
		}
		return b.toString();
	}

}
