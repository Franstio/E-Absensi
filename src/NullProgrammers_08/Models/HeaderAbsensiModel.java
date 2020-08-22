/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08.Models;

import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author DRONE003
 */
public class HeaderAbsensiModel extends ModelsBase {
    public String id;
    public String id_kelas;
    public Timestamp AbsenDate;
    public Integer Dosen;
    public Integer total_mahasiswa=0;
    public Integer total_hadir=0;
    public Integer total_sakit=0;
    public Integer total_izin=0;
    public Integer total_alpha=0;
}
