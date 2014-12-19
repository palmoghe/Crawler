/**
 * ASME Tracks
 * @author Pallavi
 *
 */
public enum Track {

	AEROSPACE(1,9208,"Aerospace Industry",90),
	APPLIED_MECHANICS(2,9210,"Applied Mechanics",1761),
	AUTOMOTIVE(3,9211,"Automotive Systems",75),
	BIOMECH(4,9212,"Biomechanical Engineering",267),
	BOILERS(5,9213,"Boilers & Pressure Vessels",371),
	BUILDING(6,9217,"Building & Construction",81),
	CAD(7,9214,"Computer-Aided Design (CAD)",226),
	CIE(8,9216,"Computers & Information in Engineering",283),
	POWER(9,9234,"Conventional Power & Fuels",159),
	DEFENSE(10,9209,"Defense Industry",26),
	DESIGN(11,9218,"Design Engineering",598),
	DYNAMIC_SYSTEMS(12,9219,"Dynamic Systems & Control",381),
	ELEC(13,9220,"Electronic & Photonic Packaging",83),
	ENERGY(14,9221,"Energy",1066),
	ETM(15,9226,"Engineering Technology Management",54),
	ENVIRONMENT(16,9222,"Environment Engineering",54),
	FLUIDS(17,9223,"Fluids Engineering",1448),
	HEAT(18,9224,"Heat Transfer",908),
	ICE(19,9225,"Internal Combustion Engines",1203),
	MANUFACTURING(20,9236,"Manufacturing & Processing",614),
	NANOTECH(21,9227,"Nanotechnology",98),
	NOISE(22,9228,"Noise Control & Acoustics",371),
	NONDES(23,9229,"Nondestructive Evaluation",12),
	NUCLEAR(24,9230,"Nuclear Engineering",45),
	NUMERICAL(25,9235,"Numerical Analysis",260),
	OCEAN(26,9237,"Ocean, Offshore & Arctic Engineering",143),
	PIPELINE(27,9238,"Pipeline & Piping Systems",325),	
	RENEWABLE(28,9231,"Renewable Energy",199),
	ROBOTICS(29,9239,"Robotics & Mechatronics",266),
	TRANSPORT(30,9232,"Transporation",112),
	TRIBOLOGY(31,9233,"Tribology",576),
	;
	
	public int trackID;
	public int categoryID;
	public String title;
	public int lastPage;
	
	private Track(int trackID,int categoryID,String title, int lastPage){
		this.trackID = trackID;
		this.categoryID = categoryID;
		this.title = title;
		this.lastPage = lastPage;
	}
}
