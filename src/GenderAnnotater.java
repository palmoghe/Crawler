import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
//import com.mashape.unirest.http.HttpResponse;


public class GenderAnnotater {

	static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36";
	static DB db = new DB();
	public static void main(String[] args) {
		try {
			//			sendRequest("http://api.genderize.io/?name[2967]=Teik-Cheng&name[2968]=Roozbeh&name[2969]=Surya&name[2970]=Kerim&name[2971]=Pierpaolo&name[2972]=Xiangpeng&name[2973]=Jinjin&name[2974]=Jinyang&name[2975]=Jong-Yun&name[2976]=Hwan-Sik&name[2977]=Yongge&name[2978]=Rouzbeh&name[2979]=Carrie&name[2980]=Ana&name[2981]=Senad&name[2982]=Marija&name[2983]=Aly&name[2984]=Chongmin&name[2985]=Jeong-Uk&name[2986]=Myoung-Gyu&name[2987]=Jeng-Tzong&name[2988]=Jia-Wei&name[2989]=Ying-Te&name[2990]=Wen-Che&name[2991]=Soham&name[2992]=Bumsoo&name[2993]=Hitesh&name[2994]=Manel&name[2995]=Dinesh&name[2996]=Anilchandra&name[2997]=Rubens&name[2998]=Colleen&name[2999]=Hallie&name[3000]=Janna&name[3001]=Bahar&name[3002]=Will&name[3003]=Zhuangjian&name[3004]=Huanyu&name[3005]=Heidi&name[3006]=Traian&name[3007]=Juekuan&name[3008]=Yandong&name[3009]=Deyu&name[3010]=Feng-Lian&name[3011]=Yue-Sheng&name[3012]=Chuanzeng&name[3013]=Gui-Lan&name[3014]=Mingxin&name[3015]=Shengfu&name[3016]=Yunxia&name[3017]=Zhongshan&name[3018]=Guimin&name[3019]=Jialu&name[3020]=Xiaoyuan&name[3021]=Abbas&name[3022]=Zhuhui&name[3023]=Jinshan&name[3024]=Ala'a&name[3025]=Placid&name[3026]=Mostafa&name[3027]=Zhanxuan&name[3028]=Shuang&name[3029]=Changhai&name[3030]=Lili&name[3031]=Shuqian&name[3032]=Prahalad&name[3033]=Omer&name[3034]=Ranga&name[3035]=Murat&name[3036]=Pol&name[3037]=Lizhong&name[3038]=Huaiyong&name[3039]=S.-S.&name[3040]=X.-L.&name[3041]=Dongxiang&name[3042]=Jingming&name[3043]=Yunong&name[3044]=Wolf&name[3045]=Jean-Baptiste&name[3046]=Zhanghai&name[3047]=Sangil&name[3048]=Xueyuan&name[3049]=Benxin&name[3050]=Ze&name[3051]=Jane&name[3052]=Itsuro&name[3053]=Osama&name[3054]=Zhengchun&name[3055]=Wasiu&name[3056]=Dewey&name[3057]=Jun-Sik&name[3058]=Kon-Well&name[3059]=Chien-Min&name[3060]=Hamad&name[3061]=Firdaus&name[3062]=Thanapat&name[3063]=Kyung-Hee&name[3064]=Chenchen&name[3065]=Shuling&name[3066]=Sulin&name[3067]=Yanhua&name[3068]=Kazunari&name[3069]=Gui&name[3070]=Xiao-Dong&name[3071]=Fanghao&name[3072]=Man&name[3073]=Pawan&name[3074]=Chiang&name[3075]=Poh&name[3076]=Chunji&name[3077]=Xinxiang&name[3078]=Hsiu-hung&name[3079]=Zhou&name[3080]=Chung-lung&name[3081]=Qiming&name[3082]=Xuanhe&name[3083]=Ran&name[3084]=Thao&name[3085]=Harold&name[3086]=Xiaoling&name[3087]=Yara&name[3088]=Kotur&name[3089]=Vishesh&name[3090]=Subrata&name[3091]=Dilip&name[3092]=Padmanabhan&name[3093]=Pankajkumar&name[3094]=Veera&name[3095]=Dilipkumar&name[3096]=Janaki&name[3097]=Jayaraman&name[3098]=Shravankumar&name[3099]=Qingdong&name[3100]=Houston&name[3101]=Sakthivel&name[3102]=Mathiyalagan&name[3103]=Selvi&name[3104]=Mingfeng&name[3105]=Teik&name[3106]=Ming-Ran&name[3107]=Ming-Te&name[3108]=Woohyun&name[3109]=Kunsoo&name[3110]=Loucas&name[3111]=Werner&name[3112]=Avesta&name[3113]=Fereydoon&name[3114]=Ebrahim&name[3115]=Jiahang&name[3116]=Datong&name[3117]=Arunvel&name[3118]=Salimzhan&name[3119]=Asgat&name[3120]=Lauri&name[3121]=Heikki&name[3122]=Pavel&name[3123]=Juha&name[3124]=Laurila&name[3125]=Alfredo&name[3126]=Aizaz&name[3127]=Dione&name[3128]=Ubiratan&name[3129]=Sangchul&name[3130]=Dohoy&name[3131]=Byungchan&name[3132]=Fengjun&name[3133]=Naseem&name[3134]=Katharina&name[3135]=Liselott&name[3136]=Petter&name[3137]=Janez&name[3138]=Petro&name[3139]=Volodymyr&name[3140]=Nadiya&name[3141]=Berna&name[3142]=Alexey&name[3143]=Irina&name[3144]=Olga&name[3145]=Smiljko&name[3146]=Blanka&name[3147]=Sandesh&name[3148]=Jaekwan&name[3149]=Ikjin&name[3150]=Fadi&name[3151]=Shenouda&name[3152]=Tamer&name[3153]=Naeim&name[3154]=Akram&name[3155]=Carolina&name[3156]=Demetrio&name[3157]=Xian&name[3158]=Pierluigi&name[3159]=Stanislav&name[3160]=Fang&name[3161]=Shumin&name[3162]=Yaqin&name[3163]=Engang&name[3164]=Chih-Hsien&name[3165]=Chyuan-Yow&name[3166]=Shiunn-Cheng&name[3167]=Rosa&name[3168]=Efstathios&name[3169]=Reginald&name[3170]=Naga&name[3171]=Chih-Hang&name[3172]=Kate&name[3173]=Brigitte&name[3174]=Huaining&name[3175]=Shun&name[3176]=Karan&name[3177]=Inho&name[3178]=Jeongsam&name[3179]=Zhongke&name[3180]=Toshihisa&name[3181]=Yoshiyuki&name[3182]=Masaaki&name[3183]=Nassib&name[3184]=Gong&name[3185]=Ren-Jye");
			//			sendRequest("http://api.genderize.io/?name[0]=Kazuo&name[1]=Osamu&name[2]=Monaco&name[3]=John&name[4]=Damian&name[5]=Torsten&name[6]=Bo&name[7]=Qiang&name[8]=Lei&name[9]=Lianhui&name[10]=Chuangang&name[11]=Ping&name[12]=Maria&name[13]=Stefano&name[14]=Antonio&name[15]=Chris&name[16]=Mark&name[17]=Simon&name[18]=Bernd&name[19]=Eitenne&name[20]=Ignacio&name[21]=Tony&name[22]=Ahmed&name[23]=Benjamin&name[24]=Chen&name[25]=Lixi&name[26]=Dipankar&name[27]=Satish&name[28]=Andreas&name[29]=Zoltán&name[30]=Wesley&name[31]=Becky&name[32]=Di&name[33]=Sheng&name[34]=Jing&name[35]=Kok&name[36]=Iman&name[37]=Kamel&name[38]=Morteza&name[39]=Zongyong&name[40]=Jiayu&name[41]=Jianhua&name[42]=Simone&name[43]=Giovanni&name[44]=Nicholas&name[45]=Kerry&name[46]=F.&name[47]=Joel&name[48]=Alain&name[49]=Mathias&name[50]=Antoine&name[51]=Sébastien&name[52]=François&name[53]=Francesco&name[54]=Alessandro&name[55]=Riccardo&name[56]=Lorenzo&name[57]=Matthias&name[58]=Davide&name[59]=Wolfram&name[60]=Hans-Jörg&name[61]=Nicolás&name[62]=Guillaume&name[63]=Roger&name[64]=Gérard&name[65]=Filippo&name[66]=Michele&name[67]=Matteo&name[68]=Juri&name[69]=Andrea&name[70]=Ralf&name[71]=Stefan&name[72]=Anthony&name[73]=Michel&name[74]=Anders&name[75]=Martin&name[76]=Milt&name[77]=Florian&name[78]=Yong&name[79]=Wang&name[80]=Wei&name[81]=Mo&name[82]=Bruce&name[83]=Jennifer&name[84]=Chuck&name[85]=Y.&name[86]=D.&name[87]=David&name[88]=Craig&name[89]=Todd&name[90]=William&name[91]=Feng&name[92]=XiaoQi&name[93]=JinYou&name[94]=Joshua&name[95]=Metin&name[96]=A.&name[97]=J.&name[98]=Aaron&name[99]=Clayton&name[100]=Tracey&name[101]=Peter&name[102]=James&name[103]=Andrew&name[104]=A.R.&name[105]=Khashayar&name[106]=Chuang&name[107]=Jingyan&name[108]=Konstantinos&name[109]=Ioannis&name[110]=Alexios&name[111]=Nikolaos&name[112]=Giorgos&name[113]=Kostas&name[114]=Mohammad&name[115]=Jorge&name[116]=Mireya&name[117]=Ryan&name[118]=Christoforos&name[119]=Alexander&name[120]=Thomas&name[121]=Brice&name[122]=Apostolos&name[123]=Michael&name[124]=Dimitrios&name[125]=Zinon&name[126]=Kyriakos&name[127]=Neeraj&name[128]=Ratnadeep&name[129]=Sam&name[130]=Jiaqi&name[131]=Zhaoguang&name[132]=Mian&name[133]=Gonzalo&name[134]=Shawn&name[135]=Vassilios&name[136]=Fakhre&name[137]=Roberto&name[138]=Robert&name[139]=Jan&name[140]=Stephan&name[141]=Yavuz&name[142]=Amir&name[143]=Pascal&name[144]=Philippe&name[145]=Carlo&name[146]=Bruno&name[147]=Marco&name[148]=Alberto&name[149]=Luca&name[150]=M.&name[151]=N.&name[152]=Masato&name[153]=Eiji&name[154]=Nobuhiro&name[155]=Tsuneaki&name[156]=B.&name[157]=Crystal&name[158]=H.&name[159]=W.&name[160]=G.&name[161]=T.&name[162]=X.&name[163]=Zhizhong&name[164]=Yanrong&name[165]=Xianghua&name[166]=Dasheng&name[167]=Joseph&name[168]=Ajay&name[169]=Thorsten&name[170]=Franz&name[171]=Dominik&name[172]=Carlos&name[173]=Roque&name[174]=Rahul&name[175]=Jianguo&name[176]=Shiyu&name[177]=Xiaochun&name[178]=Christina&name[179]=Donald&name[180]=Pamela&name[181]=Geofrey&name[182]=Jeffrey&name[183]=Tommy&name[184]=Zhenhong&name[185]=Jay&name[186]=Archer&name[187]=Hsi-Wu&name[188]=Mina&name[189]=Jonathan&name[190]=Scott&name[191]=Ian&name[192]=Richard&name[193]=Yashwanth&name[194]=Aimy&name[195]=Mary&name[196]=Liu&name[197]=Adel&name[198]=Akila&name[199]=Mauro");
			sendGet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void sendGet() throws Exception {

		//		String url = "http://api.genderize.io/?name[0]=peter&name[1]=lois&name[2]=stevie";

		File fout = new File("links.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		String baseURL = "http://api.genderize.io/?";
		String requestURL = "http://api.genderize.io/?";
		List<String> authorNames = db.getAuthorNames();
		int i=0;
		for(String authorName:authorNames){
			if(authorName.isEmpty()){
				continue;
			}
			requestURL = requestURL.concat("name["+ i++ +"]"+"="+authorName+"&");
			if(requestURL.length()>=8100 || authorNames.indexOf(authorName)==authorNames.size()-1){
				System.out.println("Sending request for URL:"+requestURL);
//				sendRequest(requestURL);
				bw.write(requestURL);
				bw.newLine();
				requestURL=baseURL;
				i=0;
			}
		}
		bw.close();
		// Remove the last ampersand
		//		requestURL = requestURL.substring(0,requestURL.length()-1);
		//		System.out.println("Request URL:"+requestURL);
		//		sendRequest(requestURL);

	}

	private static void sendRequest(String requestURL)
			throws MalformedURLException, IOException, ProtocolException {
		URL obj = new URL(requestURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		if(responseCode == 200){
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			//			JSONObject json = new JSONObject(response.toString());

		}else{
			System.out.println("\nSending 'GET' request to URL : " + requestURL);
			System.out.println("Response Code : " + responseCode);
		}
	}

	//	static void sendGet2() throws IOException{
	//		Document doc = null;
	//		System.out.println(Jsoup
	//				.connect("http://asmedigitalcollection.asme.org/collection.aspx?categoryID=9208&pageA=100&contentType=0").ignoreContentType(true).execute().body());
	//		//				.timeout(0)
	//		//				.userAgent(
	//		//						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36")
	//		//				.get();
	//	}
}
