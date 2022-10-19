package urna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import urna.model.Candidato;
import urna.model.Eleitor;

public class UrnaDao {
	
	public Eleitor buscaEleitorPeloTitulo(int titulo) {
		
		String sql = "Select * FROM Eleitor WHERE titulo = ?";
		PreparedStatement pStatement = null;
		Connection conn = null;
		ResultSet rs = null;
		Eleitor eleitor = null;
		
		try {
			conn = new PostgreSqlConnection().getConnection();
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, titulo);
			rs = pStatement.executeQuery();
			
			if(rs != null && rs.next()) {
				eleitor = new Eleitor(rs.getString("nome"), rs.getString("sobrenome"), rs.getInt("idade"), rs.getInt("titulo"));
				eleitor.setVotou(rs.getBoolean("votou"));
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStatement != null)
					pStatement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return eleitor;
	}
	
	public void atualizaVotoEleitor(int titulo) {
		String sql = "UPDATE eleitor SET votou = ? WHERE titulo = ?";
		PreparedStatement pStatement = null;
		Connection conn = null;
		
		try {
			conn = new PostgreSqlConnection().getConnection();
			pStatement = conn.prepareStatement(sql);
			pStatement.setBoolean(1, true);
			pStatement.setInt(2, titulo);
			pStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStatement != null)
					pStatement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public Candidato buscaCandidatoPorNumero(int numero) {
		if (numero == 0)
			numero = 99999999;
		else if (numero == -1)
			numero = 0;
		
		Candidato candidato = encontraCandidatoPorNumero(numero);
		if (candidato == null)
			candidato = encontraCandidatoPorNumero(99999999);
		
		return candidato;
	}
	
	private Candidato encontraCandidatoPorNumero(int numero) {
		
		String sql = "Select * From Candidato join Eleitor on titulo = ctitulo \r\n"
						+ "Where numero = ?";
		PreparedStatement pStatement = null;
		Connection conn = null;
		ResultSet rs = null;
		Candidato candidato = null;
		
		try {
			conn = new PostgreSqlConnection().getConnection();
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, numero);
			
			rs = pStatement.executeQuery();
			
			if(rs != null && rs.next()) {
				candidato = new Candidato(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("partido"), rs.getInt("idade"),
						rs.getInt("titulo") , rs.getInt("numero"));
				candidato.setVotos(rs.getInt("numvotos"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStatement != null)
					pStatement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return candidato;
	}

	public ArrayList<Candidato> buscarCandidatos(String cargo){
		int numeroCandidato = 0;
		int numeroCandidatoLimite = 0;
		if (cargo.equals("deputado")) {
			numeroCandidato = 10000;
			numeroCandidatoLimite = 999999;
		}
		else if (cargo.equals("presidente")) {
			numeroCandidato = 10;
			numeroCandidatoLimite = 99;
		}
		
		String sql = "SELECT * FROM Candidato JOIN Eleitor ON titulo = ctitulo";
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pStatement = null;
		Candidato candidato = null;
		ArrayList<Candidato> candidatos = null;
		
		try {
			conn = new PostgreSqlConnection().getConnection();
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			if (rs != null) {
				candidatos = new ArrayList<Candidato>();
				
				while (rs.next()) {
					candidato = new Candidato(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("partido"), rs.getInt("idade"),
							rs.getInt("titulo") , rs.getInt("numero"));
					
					if(numeroCandidato <= candidato.getNumeroIdentificador() && candidato.getNumeroIdentificador() <= numeroCandidatoLimite) {
						candidato.setVotos(rs.getInt("numvotos"));
						candidatos.add(candidato);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStatement != null)
					pStatement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return candidatos;
	}
	
	public ArrayList<Candidato> buscarBrancoNulo(){
		
		String sql = "SELECT * FROM Candidato JOIN Eleitor ON titulo = ctitulo WHERE ctitulo = 0 or ctitulo = 99999999";
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pStatement = null;
		Candidato candidato = null;
		ArrayList<Candidato> candidatos = null;
		
		try {
			conn = new PostgreSqlConnection().getConnection();
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			if (rs != null) {
				candidatos = new ArrayList<Candidato>();
				
				while (rs.next()) {
					candidato = new Candidato(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("partido"), rs.getInt("idade"),
							rs.getInt("titulo") , rs.getInt("numero"));
					candidato.setVotos(rs.getInt("numVotos"));
					candidatos.add(candidato);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStatement != null)
					pStatement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return candidatos;
	}
		
		public void computarVoto(int voto, int numero) {
			String sql = "UPDATE Candidato SET 	numvotos = ? WHERE 	numero = ?";
			PreparedStatement pStatement = null;
			Connection conn = null;
			
			try {
				conn = new PostgreSqlConnection().getConnection();
				pStatement = conn.prepareStatement(sql);
				pStatement.setInt(1, voto);
				pStatement.setInt(2, numero);
				pStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pStatement != null)
						pStatement.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		public void reiniciarVotacao() {
			String sql = "UPDATE Candidato SET numvotos = 0; UPDATE Eleitor SET votou = false;";
			PreparedStatement pStatement = null;
			Connection conn = null;
			
			try {
				conn = new PostgreSqlConnection().getConnection();
				pStatement = conn.prepareStatement(sql);
				pStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pStatement != null)
						pStatement.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				try {
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		

}
