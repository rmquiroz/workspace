
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@WebServlet({"/TeaServlets"})
public class TeaServlets
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  private Map reportes;
  
  public TeaServlets()
  {
    this.reportes = new HashMap();
    this.reportes.put("1", "tea");
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    createReport(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    createReport(request, response);
  }
  
  protected void createReport(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    SimpleDateFormat anioActual = new SimpleDateFormat("yyyy");
    
    System.out.println("GENERANDO REPORTE ONLINE....");
    response.setContentType("application/pdf");
    try
    {
      Class.forName("org.postgresql.Driver");
      
      String url = "jdbc:postgresql://10.1.250.20:5932/openbravo";
      Connection cn = DriverManager.getConnection(url, "postgres", "s3st2m1s4e");
      
      JasperReport jasperReport = null;
      Map parameterMap = new HashMap();
      Map parametros = request.getParameterMap();
      System.out.println("PARAMETROS .... ");
      for (Iterator iterator = parametros.keySet().iterator(); iterator.hasNext();)
      {
        Object key_ = iterator.next();
        String key = (String)key_;
        String valor = request.getParameter(key);
        System.out.println(key + " ::> " + valor);
        if (!key.equals("reportID")) {
          parameterMap.put(key, valor);
        }
      }
      parameterMap.put("IMG_DIR", request.getSession().getServletContext().getRealPath(IMG_DIR) + "/");
      parameterMap.put("SUBREPORT_DIR", request.getSession().getServletContext().getRealPath("/"));
      System.out.println("GENERANDO EL REPORTE  " + (String)this.reportes.get(request.getParameter("reportID")));
      String reporteJasper = request.getSession().getServletContext().getRealPath(PATH_REPORT + (String)this.reportes.get(request.getParameter("reportID")) + ".jasper");
      jasperReport = (JasperReport)JRLoader.loadObject(new FileInputStream(reporteJasper));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, cn);
      OutputStream oS = null;
      oS = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, oS);
      cn.close();
    }
    catch (SQLException ex)
    {
      ex.printStackTrace();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
  
  private static String PATH_REPORT = "/reportes/";
  private static String IMG_DIR = PATH_REPORT + "img/";
}