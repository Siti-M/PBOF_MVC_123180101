/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class MhsMVC {
    MhsView mhsview = new MhsView();
    MhsModel mhsmodel = new MhsModel();
    MhsDAO mhsDAO = new MhsDAO();
    MhsController mhscontroller = new MhsController(mhsmodel, mhsview, mhsDAO);
}
