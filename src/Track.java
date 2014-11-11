
public enum Track {

	AEROSPACE(1,9208,"Aerospace Industry"),
	APPLIED_MECHANICS(2,9210,"Applied Mechanics"),
	AUTOMOTIVE(3,9211,"Automotive Systems"),
	BIOMECH(4,9212,"Biomechanical Engineering"),
	BOILERS(5,9213,"Boilers & Pressure Vessels"),
	BUILDING(6,9217,"Building & Construction"),
	CAD(7,9214,"Computer-Aided Design (CAD)"),
	CIE(8,9216,"Computers & Information in Engineering"),
	POWER(9,9234,"Conventional Power & Fuels"),
	DEFENSE(10,9209,"Defense Industry"),
	DESIGN(11,9218,"Design Engineering"),
	DYNAMIC_SYSTEMS(12,9219,"Dynamic Systems & Control"),
	ELEC(13,9220,"Electronic & Photonic Packaging"),
	ENERGY(14,9221,"Energy"),
	ETM(15,9226,"Energy Technology Management"),
	ENVIRONMENT(16,9222,"Environmental Engineering"),
	FLUIDS(17,9223,"Fluids Engineering"),
	HEAT(18,9224,"Heat Transfer"),
	ICE(19,9225,"Internal Combustion Engines"),
	MANUFACTURING(20,9236,"Manufacturing & Processing"),
	NANOTECH(21,9227,"Nanotechnology"),
	NOISE(22,9228,"Noise Control & Acoustics"),
	NONDES(23,9229,"Nondestructive Evaluation"),
	NUCLEAR(24,9230,"Nuclear Engineering"),
	NUMERICAL(25,9235,"Numerical Analysis"),
	OCEAN(26,9237,"Ocean, Offshore & Arctic Engineering"),
	PIPELINE(27,9238,"Pipeline & Piping Systems"),	
	RENEWABLE(28,9231,"Renewable Energy"),
	ROBOTICS(29,9239,"Robotics & Mechatronics"),
	TRANSPORT(30,9232,"Transporation"),
	TRIBOLOGY(31,9233,"Tribology"),
	;
	
	public int trackID;
	public int categoryID;
	public String title;
	
	private Track(int trackID,int categoryID,String title){
		this.trackID = trackID;
		this.categoryID = categoryID;
		this.title = title;
	}
}
