import java.io.BufferedReader;
import java.io.InputStreamReader;


public class JSONParser {

	
	public static void main(String[] args) {

//		String response = "[{"name":"Kazuo","gender":"male","probability":"1.00","count":4},{"name":"Osamu","gender":"male","probability":"1.00","count":3},{"name":"Monaco","gender":"male","probability":"1.00","count":1},{"name":"John","gender":"male","probability":"0.99","count":5986},{"name":"Damian","gender":"male","probability":"0.99","count":258},{"name":"Torsten","gender":"male","probability":"1.00","count":35},{"name":"Bo","gender":"male","probability":"0.83","count":156},{"name":"Qiang","gender":"male","probability":"1.00","count":4},{"name":"Lei","gender":"female","probability":"0.53","count":38},{"name":"Lianhui","gender":null},{"name":"Chuangang","gender":null},{"name":"Ping","gender":"female","probability":"0.56","count":36},{"name":"Maria","gender":"female","probability":"0.99","count":5098},{"name":"Stefano","gender":"male","probability":"1.00","count":560},{"name":"Antonio","gender":"male","probability":"1.00","count":1657},{"name":"Chris","gender":"male","probability":"0.91","count":4828},{"name":"Mark","gender":"male","probability":"1.00","count":3451},{"name":"Simon","gender":"male","probability":"0.99","count":1258},{"name":"Bernd","gender":"male","probability":"1.00","count":28},{"name":"Eitenne","gender":null},{"name":"Ignacio","gender":"male","probability":"1.00","count":244},{"name":"Tony","gender":"male","probability":"0.99","count":1668},{"name":"Ahmed","gender":"male","probability":"0.99","count":1347},{"name":"Benjamin","gender":"male","probability":"1.00","count":859},{"name":"Chen","gender":"male","probability":"0.50","count":103},{"name":"Lixi","gender":null},{"name":"Dipankar","gender":"male","probability":"1.00","count":9},{"name":"Satish","gender":"male","probability":"0.99","count":73},{"name":"Andreas","gender":"male","probability":"1.00","count":523},{"name":"Zolt\u00e1n","gender":"male","probability":"1.00","count":29},{"name":"Wesley","gender":"male","probability":"0.99","count":236},{"name":"Becky","gender":"female","probability":"1.00","count":867},{"name":"Di","gender":"female","probability":"0.73","count":103},{"name":"Sheng","gender":"male","probability":"0.56","count":16},{"name":"Jing","gender":"female","probability":"0.82","count":71},{"name":"Kok","gender":"male","probability":"1.00","count":5},{"name":"Iman","gender":"male","probability":"0.51","count":89},{"name":"Kamel","gender":"male","probability":"1.00","count":48},{"name":"Morteza","gender":"male","probability":"1.00","count":10},{"name":"Zongyong","gender":null},{"name":"Jiayu","gender":"female","probability":"1.00","count":1},{"name":"Jianhua","gender":null},{"name":"Simone","gender":"male","probability":"0.53","count":692},{"name":"Giovanni","gender":"male","probability":"1.00","count":530},{"name":"Nicholas","gender":"male","probability":"1.00","count":611},{"name":"Kerry","gender":"female","probability":"0.84","count":455},{"name":"F.","gender":null},{"name":"Joel","gender":"male","probability":"0.99","count":837},{"name":"Alain","gender":"male","probability":"1.00","count":335},{"name":"Mathias","gender":"male","probability":"0.98","count":168},{"name":"Antoine","gender":"male","probability":"1.00","count":277},{"name":"S\u00e9bastien","gender":"male","probability":"1.00","count":477},{"name":"Fran\u00e7ois","gender":"male","probability":"0.99","count":348},{"name":"Francesco","gender":"male","probability":"1.00","count":850},{"name":"Alessandro","gender":"male","probability":"1.00","count":767},{"name":"Riccardo","gender":"male","probability":"1.00","count":210},{"name":"Lorenzo","gender":"male","probability":"1.00","count":354},{"name":"Matthias","gender":"male","probability":"1.00","count":177},{"name":"Davide","gender":"male","probability":"1.00","count":411},{"name":"Wolfram","gender":"male","probability":"1.00","count":4},{"name":"Hans-J\u00f6rg","gender":"male","probability":"1.00","count":1},{"name":"Nicol\u00e1s","gender":"male","probability":"1.00","count":1240},{"name":"Guillaume","gender":"male","probability":"1.00","count":445},{"name":"Roger","gender":"male","probability":"1.00","count":709},{"name":"G\u00e9rard","gender":"male","probability":"1.00","count":235},{"name":"Filippo","gender":"male","probability":"1.00","count":137},{"name":"Michele","gender":"female","probability":"0.67","count":1028},{"name":"Matteo","gender":"male","probability":"1.00","count":405},{"name":"Juri","gender":"male","probability":"0.86","count":14},{"name":"Andrea","gender":"female","probability":"0.74","count":3633},{"name":"Ralf","gender":"male","probability":"1.00","count":59},{"name":"Stefan","gender":"male","probability":"0.99","count":530},{"name":"Anthony","gender":"male","probability":"1.00","count":1733},{"name":"Michel","gender":"male","probability":"0.95","count":429},{"name":"Anders","gender":"male","probability":"1.00","count":258},{"name":"Martin","gender":"male","probability":"1.00","count":2110},{"name":"Milt","gender":"male","probability":"1.00","count":3},{"name":"Florian","gender":"male","probability":"1.00","count":304},{"name":"Yong","gender":"male","probability":"0.65","count":37},{"name":"Wang","gender":"male","probability":"0.68","count":38},{"name":"Wei","gender":"male","probability":"0.62","count":90},{"name":"Mo","gender":"male","probability":"0.62","count":151},{"name":"Bruce","gender":"male","probability":"0.99","count":454},{"name":"Jennifer","gender":"female","probability":"1.00","count":4016},{"name":"Chuck","gender":"male","probability":"0.99","count":315},{"name":"Y.","gender":null},{"name":"D.","gender":null},{"name":"David","gender":"male","probability":"1.00","count":7530},{"name":"Craig","gender":"male","probability":"1.00","count":945},{"name":"Todd","gender":"male","probability":"1.00","count":536},{"name":"William","gender":"male","probability":"1.00","count":1685},{"name":"Feng","gender":"male","probability":"0.62","count":21},{"name":"XiaoQi","gender":"female","probability":"1.00","count":1},{"name":"JinYou","gender":null},{"name":"Joshua","gender":"male","probability":"1.00","count":918},{"name":"Metin","gender":"male","probability":"1.00","count":171},{"name":"A.","gender":null},{"name":"J.","gender":null},{"name":"Aaron","gender":"male","probability":"0.99","count":1333},{"name":"Clayton","gender":"male","probability":"1.00","count":130},{"name":"Tracey","gender":"female","probability":"0.99","count":542},{"name":"Peter","gender":"male","probability":"1.00","count":2383},{"name":"James","gender":"male","probability":"0.99","count":3466},{"name":"Andrew","gender":"male","probability":"1.00","count":2805},{"name":"A.R.","gender":"male","probability":"1.00","count":1},{"name":"Khashayar","gender":"male","probability":"1.00","count":1},{"name":"Chuang","gender":"male","probability":"0.75","count":4},{"name":"Jingyan","gender":null},{"name":"Konstantinos","gender":"male","probability":"1.00","count":68},{"name":"Ioannis","gender":"male","probability":"1.00","count":24},{"name":"Alexios","gender":null},{"name":"Nikolaos","gender":"male","probability":"1.00","count":13},{"name":"Giorgos","gender":"male","probability":"1.00","count":104},{"name":"Kostas","gender":"male","probability":"1.00","count":127},{"name":"Mohammad","gender":"male","probability":"1.00","count":504},{"name":"Jorge","gender":"male","probability":"1.00","count":1828},{"name":"Mireya","gender":"female","probability":"0.99","count":71},{"name":"Ryan","gender":"male","probability":"0.99","count":2668},{"name":"Christoforos","gender":"male","probability":"1.00","count":5},{"name":"Alexander","gender":"male","probability":"1.00","count":963},{"name":"Thomas","gender":"male","probability":"1.00","count":2246},{"name":"Brice","gender":"male","probability":"1.00","count":67},{"name":"Apostolos","gender":"male","probability":"1.00","count":10},{"name":"Michael","gender":"male","probability":"1.00","count":6407},{"name":"Dimitrios","gender":"male","probability":"1.00","count":23},{"name":"Zinon","gender":null},{"name":"Kyriakos","gender":"male","probability":"1.00","count":17},{"name":"Neeraj","gender":"male","probability":"0.95","count":58},{"name":"Ratnadeep","gender":null},{"name":"Sam","gender":"male","probability":"0.76","count":1792},{"name":"Jiaqi","gender":"female","probability":"1.00","count":1},{"name":"Zhaoguang","gender":null},{"name":"Mian","gender":"male","probability":"0.75","count":12},{"name":"Gonzalo","gender":"male","probability":"0.99","count":363},{"name":"Shawn","gender":"male","probability":"0.89","count":626},{"name":"Vassilios","gender":"male","probability":"1.00","count":2},{"name":"Fakhre","gender":null},{"name":"Roberto","gender":"male","probability":"0.99","count":1292},{"name":"Robert","gender":"male","probability":"1.00","count":3013},{"name":"Jan","gender":"male","probability":"0.59","count":1024},{"name":"Stephan","gender":"male","probability":"0.99","count":175},{"name":"Yavuz","gender":"male","probability":"0.98","count":92},{"name":"Amir","gender":"male","probability":"0.99","count":289},{"name":"Pascal","gender":"male","probability":"0.99","count":315},{"name":"Philippe","gender":"male","probability":"0.99","count":389},{"name":"Carlo","gender":"male","probability":"1.00","count":293},{"name":"Bruno","gender":"male","probability":"1.00","count":628},{"name":"Marco","gender":"male","probability":"1.00","count":1753},{"name":"Alberto","gender":"male","probability":"1.00","count":957},{"name":"Luca","gender":"male","probability":"0.99","count":742},{"name":"M.","gender":null},{"name":"N.","gender":null},{"name":"Masato","gender":"male","probability":"1.00","count":10},{"name":"Eiji","gender":"male","probability":"1.00","count":3},{"name":"Nobuhiro","gender":"male","probability":"1.00","count":1},{"name":"Tsuneaki","gender":null},{"name":"B.","gender":null},{"name":"Crystal","gender":"female","probability":"1.00","count":709},{"name":"H.","gender":"male","probability":"1.00","count":1},{"name":"W.","gender":null},{"name":"G.","gender":null},{"name":"T.","gender":"female","probability":"0.50","count":2},{"name":"X.","gender":null},{"name":"Zhizhong","gender":null},{"name":"Yanrong","gender":"female","probability":"1.00","count":2},{"name":"Xianghua","gender":null},{"name":"Dasheng","gender":null},{"name":"Joseph","gender":"male","probability":"0.99","count":1299},{"name":"Ajay","gender":"male","probability":"0.99","count":157},{"name":"Thorsten","gender":"male","probability":"1.00","count":28},{"name":"Franz","gender":"male","probability":"0.99","count":69},{"name":"Dominik","gender":"male","probability":"0.98","count":123},{"name":"Carlos","gender":"male","probability":"1.00","count":3147},{"name":"Roque","gender":"male","probability":"1.00","count":27},{"name":"Rahul","gender":"male","probability":"1.00","count":251},{"name":"Jianguo","gender":null},{"name":"Shiyu","gender":null},{"name":"Xiaochun","gender":null},{"name":"Christina","gender":"female","probability":"1.00","count":1373},{"name":"Donald","gender":"male","probability":"0.99","count":355},{"name":"Pamela","gender":"female","probability":"1.00","count":981},{"name":"Geofrey","gender":"male","probability":"1.00","count":1},{"name":"Jeffrey","gender":"male","probability":"1.00","count":569},{"name":"Tommy","gender":"male","probability":"0.98","count":559},{"name":"Zhenhong","gender":null},{"name":"Jay","gender":"male","probability":"0.92","count":1038},{"name":"Archer","gender":"female","probability":"0.50","count":2},{"name":"Hsi-Wu","gender":null},{"name":"Mina","gender":"female","probability":"0.72","count":188},{"name":"Jonathan","gender":"male","probability":"1.00","count":2113},{"name":"Scott","gender":"male","probability":"1.00","count":2113},{"name":"Ian","gender":"male","probability":"0.99","count":1144},{"name":"Richard","gender":"male","probability":"1.00","count":2490},{"name":"Yashwanth","gender":"male","probability":"1.00","count":1},{"name":"Aimy","gender":"female","probability":"0.67","count":3},{"name":"Mary","gender":"female","probability":"1.00","count":2927},{"name":"Liu","gender":"male","probability":"0.54","count":28},{"name":"Adel","gender":"male","probability":"0.94","count":125},{"name":"Akila","gender":"female","probability":"0.57","count":14},{"name":"Mauro","gender":"male","probability":"1.00","count":353},{"name":"Ryosuke","gender":"male","probability":"1.00","count":6},{"name":"Makoto","gender":"male","probability":"1.00","count":13},{"name":"Linda","gender":"female","probability":"1.00","count":2621},{"name":"Tomas","gender":"male","probability":"0.99","count":570},{"name":"E.","gender":null},{"name":"C.","gender":"male","probability":"1.00","count":1},{"name":"Ali","gender":"male","probability":"0.85","count":1962},{"name":"Cengiz","gender":"male","probability":"0.99","count":99},{"name":"Chao","gender":"male","probability":"0.79","count":14},{"name":"Arvind","gender":"male","probability":"1.00","count":38},{"name":"Nestor","gender":"male","probability":"1.00","count":176},{"name":"Ugochukwu","gender":"male","probability":"1.00","count":3},{"name":"Xiaoyu","gender":"female","probability":"0.50","count":6},{"name":"Paul","gender":"male","probability":"1.00","count":3401},{"name":"I.","gender":null},{"name":"K.","gender":null},{"name":"Walter","gender":"male","probability":"1.00","count":494},{"name":"V.","gender":null},{"name":"L.","gender":null},{"name":"R.","gender":null},{"name":"Christopher","gender":"male","probability":"1.00","count":1454},{"name":"Jimmy","gender":"male","probability":"1.00","count":794},{"name":"Dimitri","gender":"male","probability":"1.00","count":117},{"name":"Alex","gender":"male","probability":"0.87","count":3501},{"name":"Julio","gender":"male","probability":"1.00","count":614},{"name":"Ch.","gender":null},{"name":"Q.","gender":null},{"name":"P.","gender":null},{"name":"Serdar","gender":"male","probability":"0.98","count":197},{"name":"Sinan","gender":"male","probability":"0.98","count":180},{"name":"O?uz","gender":null},{"name":"?lkay","gender":null},{"name":"Stephen","gender":"male","probability":"1.00","count":1477},{"name":"Wing","gender":"female","probability":"0.63","count":56},{"name":"Hans","gender":"male","probability":"0.98","count":259},{"name":"Ulrich","gender":"male","probability":"1.00","count":27},{"name":"Kevin","gender":"male","probability":"1.00","count":3208},{"name":"Daiwei","gender":null},{"name":"Xiaocheng","gender":null},{"name":"Zhaohui","gender":null},{"name":"Rong","gender":"female","probability":"0.83","count":12},{"name":"Wen","gender":"female","probability":"0.72","count":36},{"name":"Yan","gender":"male","probability":"0.53","count":120},{"name":"Xiao","gender":"female","probability":"0.50","count":48},{"name":"S.","gender":"female","probability":"1.00","count":1},{"name":"Cyril","gender":"male","probability":"0.99","count":183},{"name":"Hans-Peter","gender":"male","probability":"1.00","count":5},{"name":"Volker","gender":"male","probability":"1.00","count":26},{"name":"Gerald","gender":"male","probability":"0.99","count":247},{"name":"Jens","gender":"male","probability":"0.99","count":180},{"name":"Jonas","gender":"male","probability":"0.99","count":313},{"name":"J\u00f6rn","gender":"male","probability":"1.00","count":22},{"name":"Toyotaka","gender":null}]";
	}
	
	void parseIntoJSON(){
//		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent())) ;
//		Object obj=JSONValue.parse(rd.toString());
	}
}
