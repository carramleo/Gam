/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlo
 */


@ManagedBean
@SessionScoped
public class newFile implements Serializable{
    
   
    
    public void writeStream(String inputContent) throws IOException {
    Writer writer = new FileWriter("outputOne.txt");

    try {
        writer.write(inputContent);
    } finally {
        writer.close();
    }
}
    
}
