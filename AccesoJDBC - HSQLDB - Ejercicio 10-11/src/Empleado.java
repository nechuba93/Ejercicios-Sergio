
public class Empleado {

	public Empleado(){
		
	}
	
	public Empleado(int emp_no, String apellido, String oficio, int dir, int salario, int comision, int dept_no) {
		super();
		this.emp_no = emp_no;
		this.apellido = apellido;
		this.oficio = oficio;
		this.dir = dir;
		this.salario = salario;
		this.comision = comision;
		this.dept_no = dept_no;
	}
	
	int emp_no;
	String apellido;
	String oficio;
	int dir;
	int salario;
	int comision;
	int dept_no;
	/**
	 * @return the emp_no
	 */
	public int getEmp_no() {
		return emp_no;
	}
	/**
	 * @param emp_no the emp_no to set
	 */
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the oficio
	 */
	public String getOficio() {
		return oficio;
	}
	/**
	 * @param oficio the oficio to set
	 */
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	/**
	 * @return the dir
	 */
	public int getDir() {
		return dir;
	}
	/**
	 * @param dir the dir to set
	 */
	public void setDir(int dir) {
		this.dir = dir;
	}
	/**
	 * @return the salario
	 */
	public int getSalario() {
		return salario;
	}
	/**
	 * @param salario the salario to set
	 */
	public void setSalario(int salario) {
		this.salario = salario;
	}
	/**
	 * @return the comision
	 */
	public int getComision() {
		return comision;
	}
	/**
	 * @param comision the comision to set
	 */
	public void setComision(int comision) {
		this.comision = comision;
	}
	/**
	 * @return the dept_no
	 */
	public int getDept_no() {
		return dept_no;
	}
	/**
	 * @param dept_no the dept_no to set
	 */
	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}
	
}
