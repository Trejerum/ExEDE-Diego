package interfaz;

import java.awt.Color;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logica.Departamento;
import logica.Empleado;
import logica.Excepciones.NumDepartDuplicado;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import javax.swing.SwingConstants;

/**
 * 
 * <h2>Esta  clase se encarga del mantenimiento de los datos de departamento, se realizan altas, bajas, modificaciones y consultas<h2>
 *
 */
@SuppressWarnings("serial")
public class OperacionesDepart extends JDialog implements InterfazDepart {
	
	private static final String BBDD="Empleados.dat";
	private Etiqueta data = new Etiqueta();

	public OperacionesDepart() {
		setTitle("Operaciones departamentos.");
		setModal(true);
		setBounds(100, 100, 450, 300);
		data.contentPane = new JPanel();
		data.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(data.contentPane);
		data.contentPane.setLayout(null);
		
		JLabel lblOperaciones = new JLabel("Operaciones DEPARTAMENTOS");
		lblOperaciones.setBackground(Color.YELLOW);
		lblOperaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperaciones.setForeground(Color.BLUE);
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOperaciones.setBounds(75, 11, 292, 35);
		data.contentPane.add(lblOperaciones);
		
		JLabel lblNumDepart = new JLabel("Num Departamento");
		lblNumDepart.setBounds(36, 74, 112, 16);
		data.contentPane.add(lblNumDepart);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(36, 102, 55, 16);
		data.contentPane.add(lblNombre);
		
		JLabel lblPoblacion = new JLabel("Poblaci\u00F3n");
		lblPoblacion.setBounds(36, 130, 69, 16);
		data.contentPane.add(lblPoblacion);
		
		data.txNumDepart = new JTextField();
		data.txNumDepart.setBounds(166, 72, 95, 20);
		data.contentPane.add(data.txNumDepart);
		data.txNumDepart.setColumns(10);
		
		data.txNombre = new JTextField();
		data.txNombre.setBounds(166, 100, 215, 20);
		data.contentPane.add(data.txNombre);
		data.txNombre.setColumns(10);
		
		data.txPoblacion = new JTextField();
		data.txPoblacion.setBounds(166, 128, 215, 20);
		data.contentPane.add(data.txPoblacion);
		data.txPoblacion.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Dialog", Font.BOLD, 10));
		btnConsultar.setBounds(292, 69, 89, 23);
		data.contentPane.add(btnConsultar);
		
		data.lblRespuesta = new JLabel("---------------------------------------------------------------------");
		data.lblRespuesta.setFont(new Font("Dialog", Font.BOLD, 14));
		data.lblRespuesta.setForeground(Color.RED);
		data.lblRespuesta.setBounds(34, 169, 345, 14);
		data.contentPane.add(data.lblRespuesta);
		
		JButton btnInsertarDepartamento = new JButton("Insertar Departamento");
		btnInsertarDepartamento.setMargin(new Insets(2, 4, 2, 4));
		btnInsertarDepartamento.setFont(new Font("Dialog", Font.BOLD, 10));
		btnInsertarDepartamento.setBounds(12, 224, 124, 26);
		data.contentPane.add(btnInsertarDepartamento);
		
		JButton btnBorrarDepartamento = new JButton("Borrar Departamento");
		btnBorrarDepartamento.setMargin(new Insets(2, 4, 2, 4));
		btnBorrarDepartamento.setFont(new Font("Dialog", Font.BOLD, 10));
		btnBorrarDepartamento.setBounds(150, 224, 124, 26);
		data.contentPane.add(btnBorrarDepartamento);
		
		JButton btnModifcarDepartamento = new JButton("Modifcar Departamento");
		btnModifcarDepartamento.setMargin(new Insets(2, 4, 2, 4));
		btnModifcarDepartamento.setFont(new Font("Dialog", Font.BOLD, 10));
		btnModifcarDepartamento.setBounds(288, 224, 129, 26);
		data.contentPane.add(btnModifcarDepartamento);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		panel.setForeground(Color.BLUE);
		panel.setBounds(12, 45, 399, 156);
		data.contentPane.add(panel);
		
		//Action listeners
		//Accion boton insertar departamento
		btnInsertarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num;
				String nom, pob;
				ODB odb=ODBFactory.open(BBDD);
				
				try{
					insertarDep(odb);
				}
				catch(NumberFormatException e){
					data.lblRespuesta.setText("Error, numero de departamento erroneo");
				}
				catch(NumDepartDuplicado e){
					data.lblRespuesta.setText("Error, "+e.getMessage());
				}
				finally{
					odb.close();
				}
			}
		});
		
		//Accion boton borrar departamento
		btnBorrarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num=0;
				ODB odb=ODBFactory.open(BBDD);
				
				try{
					borrarDep(odb);
				}
				catch(NumberFormatException e){
					data.lblRespuesta.setText("Error, numero de departamento erroneo");
				}
				finally{
					odb.close();
				}
			}
		});
		
		//Accion boton consultar departamento
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num;
				ODB odb=ODBFactory.open(BBDD);
				
				try{
					consultarDep(odb);
				}
				catch(NumberFormatException e){
					data.lblRespuesta.setText("Error, numero de departamento erroneo");
				}
				finally{
					odb.close();
				}
			}
		});
		
		//Accion boton modificar departamento
		btnModifcarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num;
				ODB odb=ODBFactory.open(BBDD);
				
				try{
					modificarDep(odb);
				}
				catch(NumberFormatException e){
					data.lblRespuesta.setText("Error, numero de departamento erroneo");
				}
				finally{
					odb.close();
				}
			}
		});
	}
	
	private void comprobarNumDepart(ODB odb, int num) throws NumDepartDuplicado{
		IQuery query=new CriteriaQuery(Departamento.class, Where.equal("dept_no", num));
		Objects<Departamento> dep=odb.getObjects(query);
		
		if(num<1)
			throw new NumDepartDuplicado("numero de departamento no valido");
		if(!dep.isEmpty())
			throw new NumDepartDuplicado("numero de departamento duplicado");
	}

	/* (non-Javadoc)
	 * @see interfaz.InterfazDepart#insertarDep(org.neodatis.odb.ODB)
	 */
	@Override
	public void insertarDep(ODB odb) throws NumDepartDuplicado {
		int num;
		String nom;
		String pob;
		num=Integer.parseInt(data.txNumDepart.getText());
		comprobarNumDepart(odb, num);
		if(!data.txNombre.getText().equals("")){
			if(!data.txPoblacion.getText().equals("")){
				nom=data.txNombre.getText();
				pob=data.txPoblacion.getText();
				odb.store(new Departamento(num,nom,pob));
				
				data.lblRespuesta.setText("Departamento insertado correctamente");
			}
			else
				data.lblRespuesta.setText("Error, poblacion vacia");
		}
		else
			data.lblRespuesta.setText("Error, nombre de departamento vacio");
	}

	/* (non-Javadoc)
	 * @see interfaz.InterfazDepart#borrarDep(org.neodatis.odb.ODB)
	 */
	@Override
	public void borrarDep(ODB odb) {
		int num;
		num=Integer.parseInt(data.txNumDepart.getText());
		IQuery query=new CriteriaQuery(Departamento.class, Where.equal("dept_no", num));
		Objects<Departamento> dep=odb.getObjects(query);
		if(!dep.isEmpty()){
			IQuery query2=new CriteriaQuery(Empleado.class, 
					Where.equal("dept.dept_no", dep.getFirst().getDept_no()));
			Objects<Empleado> emp=odb.getObjects(query2);
			for(Empleado e:emp){
				e.setDept(null);
				odb.store(e);
			}
			odb.delete(dep.getFirst());
			data.lblRespuesta.setText("Departamento borrado correctamente");
		}
		else
			data.lblRespuesta.setText("Error, el departamento no existe");
	}

	/* (non-Javadoc)
	 * @see interfaz.InterfazDepart#consultarDep(org.neodatis.odb.ODB)
	 */
	@Override
	public void consultarDep(ODB odb) {
		int num;
		num=Integer.parseInt(data.txNumDepart.getText());
		IQuery query=new CriteriaQuery(Departamento.class, Where.equal("dept_no", num));
		Objects<Departamento> dep=odb.getObjects(query);
		if(!dep.isEmpty()){
			data.txNombre.setText(dep.getFirst().getDnombre());
			data.txPoblacion.setText(dep.getFirst().getLoc());
			data.lblRespuesta.setText("Consulta satisfactoria");
		}
		else
			data.lblRespuesta.setText("Error, el departamento no existe");
	}

	/* (non-Javadoc)
	 * @see interfaz.InterfazDepart#modificarDep(org.neodatis.odb.ODB)
	 */
	@Override
	public void modificarDep(ODB odb) {
		int num;
		num=Integer.parseInt(data.txNumDepart.getText());
		IQuery query=new CriteriaQuery(Departamento.class, Where.equal("dept_no", num));
		Objects<Departamento> dep=odb.getObjects(query);
		if(!dep.isEmpty()){
			if(!data.txNombre.getText().equals("")){
				if(!data.txPoblacion.getText().equals("")){
					Departamento depar;
					depar=dep.getFirst();
					depar.setDnombre(data.txNombre.getText());
					depar.setLoc(data.txPoblacion.getText());
					odb.store(depar);
					data.lblRespuesta.setText("Modifcacion satisfactoria");
				}
				else
					data.lblRespuesta.setText("Error, poblacion vacia");
			}
			else
				data.lblRespuesta.setText("Error, nombre de departamento vacio");
		}
		else
			data.lblRespuesta.setText("Error, el departamento no existe");
	}
}
