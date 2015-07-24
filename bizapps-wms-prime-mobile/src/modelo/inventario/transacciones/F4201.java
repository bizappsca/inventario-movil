package modelo.inventario.transacciones;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import modelo.inventario.pk.F4201PK;


/**
 * The persistent class for the F4201 database table.
 * 
 */
@Entity
@Table(schema = "bizapps_wms_prime.dbo")
@NamedQuery(name="F4201.findAll", query="SELECT f FROM F4201 f")
public class F4201 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private F4201PK id;

	@Column(name="SHADDJ")
	private BigDecimal shaddj;

	@Column(name="SHADLJ")
	private BigDecimal shadlj;

	@Column(name="SHADTM")
	private Double shadtm;

	@Column(name="SHAFT")
	private String shaft;

	@Column(name="SHAN8")
	private Double shan8;

	@Column(name="SHANBY")
	private Double shanby;

	@Column(name="SHASN")
	private String shasn;

	@Column(name="SHATXT")
	private String shatxt;

	@Column(name="SHAUFI")
	private String shaufi;

	@Column(name="SHAUFT")
	private String shauft;

	@Column(name="SHAUTN")
	private String shautn;

	@Column(name="SHBACK")
	private String shback;

	@Column(name="SHBCRC")
	private String shbcrc;

	@Column(name="SHBSC")
	private String shbsc;

	@Column(name="SHCACT")
	private String shcact;

	@Column(name="SHCARS")
	private Double shcars;

	@Column(name="SHCCIDLN")
	private Double shccidln;

	@Column(name="SHCEXP")
	private BigDecimal shcexp;

	@Column(name="SHCNDJ")
	private BigDecimal shcndj;

	@Column(name="SHCNID")
	private String shcnid;

	@Column(name="SHCO")
	private String shco;

	@Column(name="SHCORD")
	private Double shcord;

	@Column(name="SHCOT")
	private String shcot;

	@Column(name="SHCRCD")
	private String shcrcd;

	@Column(name="SHCRMD")
	private String shcrmd;

	@Column(name="SHCRR")
	private Double shcrr;

	@Column(name="SHCRRM")
	private String shcrrm;

	@Column(name="SHDCT4")
	private String shdct4;

	@Column(name="SHDEL1")
	private String shdel1;

	@Column(name="SHDEL2")
	private String shdel2;

	@Column(name="SHDOC1")
	private Double shdoc1;

	@Column(name="SHDRQJ")
	private BigDecimal shdrqj;

	@Column(name="SHDRQT")
	private Double shdrqt;

	@Column(name="SHDVAN")
	private Double shdvan;

	@Column(name="SHEXR1")
	private String shexr1;

	@Column(name="SHFAP")
	private Double shfap;

	@Column(name="SHFCST")
	private Double shfcst;

	@Column(name="SHFRTC")
	private String shfrtc;

	@Column(name="SHFRTH")
	private String shfrth;

	@Column(name="SHFTAN")
	private Double shftan;

	@Column(name="SHFUF1")
	private String shfuf1;

	@Column(name="SHFUF2")
	private String shfuf2;

	@Column(name="SHHOLD")
	private String shhold;

	@Column(name="SHINMG")
	private String shinmg;

	@Column(name="SHINVC")
	private Double shinvc;

	@Column(name="SHIR01")
	private String shir01;

	@Column(name="SHIR02")
	private String shir02;

	@Column(name="SHIR03")
	private String shir03;

	@Column(name="SHIR04")
	private String shir04;

	@Column(name="SHIR05")
	private String shir05;

	@Column(name="SHITAN")
	private Double shitan;

	@Column(name="SHJOBN")
	private String shjobn;

	@Column(name="SHLNGP")
	private String shlngp;

	@Column(name="SHMCU")
	private String shmcu;

	@Column(name="SHMORD")
	private String shmord;

	@Column(name="SHMOT")
	private String shmot;

	@Column(name="SHNTR")
	private String shntr;

	@Column(name="SHOCTO")
	private String shocto;

	@Column(name="SHOKCO")
	private String shokco;

	@Column(name="SHOORN")
	private String shoorn;

	@Column(name="SHOPBA")
	private String shopba;

	@Column(name="SHOPBK")
	private Double shopbk;

	@Column(name="SHOPBO")
	private String shopbo;

	@Column(name="SHOPDJ")
	private BigDecimal shopdj;

	@Column(name="SHOPLD")
	private BigDecimal shopld;

	@Column(name="SHOPLL")
	private String shopll;

	@Column(name="SHOPMS")
	private String shopms;

	@Column(name="SHOPPID")
	private Double shoppid;

	@Column(name="SHOPPL")
	private String shoppl;

	@Column(name="SHOPPS")
	private String shopps;

	@Column(name="SHOPSB")
	private Double shopsb;

	@Column(name="SHOPSS")
	private String shopss;

	@Column(name="SHOPTC")
	private Double shoptc;

	@Column(name="SHOPTT")
	private Double shoptt;

	@Column(name="SHORBY")
	private String shorby;

	@Column(name="SHOTIND")
	private String shotind;

	@Column(name="SHOTOT")
	private Double shotot;

	@Column(name="SHPA8")
	private Double shpa8;

	@Column(name="SHPBAN")
	private Double shpban;

	@Column(name="SHPCRT")
	private Double shpcrt;

	@Column(name="SHPDDJ")
	private BigDecimal shpddj;

	@Column(name="SHPDTT")
	private Double shpdtt;

	@Column(name="SHPEFJ")
	private BigDecimal shpefj;

	@Column(name="SHPID")
	private String shpid;

	@Column(name="SHPLST")
	private String shplst;

	@Column(name="SHPMDT")
	private Double shpmdt;

	@Column(name="SHPPDJ")
	private BigDecimal shppdj;

	@Column(name="SHPRAN8")
	private Double shpran8;

	@Column(name="SHPRCIDLN")
	private Double shprcidln;

	@Column(name="SHPRGP")
	private String shprgp;

	@Column(name="SHPRIO")
	private String shprio;

	@Column(name="SHPSTM")
	private Double shpstm;

	@Column(name="SHPTC")
	private String shptc;

	@Column(name="SHRCD")
	private String shrcd;

	@Column(name="SHRCTO")
	private String shrcto;

	@Column(name="SHRKCO")
	private String shrkco;

	@Column(name="SHRORN")
	private String shrorn;

	@Column(name="SHROUT")
	private String shrout;

	@Column(name="SHRQSJ")
	private BigDecimal shrqsj;

	@Column(name="SHRSDT")
	private Double shrsdt;

	@Column(name="SHRYIN")
	private String shryin;

	@Column(name="SHSBAL")
	private String shsbal;

	@Column(name="SHSBLI")
	private String shsbli;

	@Column(name="SHSDATTN")
	private String shsdattn;

	@Column(name="SHSFXO")
	private String shsfxo;

	@Column(name="SHSHAN")
	private Double shshan;

	@Column(name="SHSHCCIDLN")
	private Double shshccidln;

	@Column(name="SHSOOR")
	private BigDecimal shsoor;

	@Column(name="SHSPATTN")
	private String shspattn;

	@Column(name="SHSTOP")
	private String shstop;

	@Column(name="SHTDAY")
	private Double shtday;

	@Column(name="SHTKBY")
	private String shtkby;

	@Column(name="SHTOTC")
	private Double shtotc;

	@Column(name="SHTRDC")
	private Double shtrdc;

	@Column(name="SHTRDJ")
	private BigDecimal shtrdj;

	@Column(name="SHTXA1")
	private String shtxa1;

	@Column(name="SHTXCT")
	private String shtxct;

	@Column(name="SHUPMJ")
	private BigDecimal shupmj;

	@Column(name="SHURAB")
	private Double shurab;

	@Column(name="SHURAT")
	private Double shurat;

	@Column(name="SHURCD")
	private String shurcd;

	@Column(name="SHURDT")
	private BigDecimal shurdt;

	@Column(name="SHURRF")
	private String shurrf;

	@Column(name="SHUSER")
	private String shuser;

	@Column(name="SHVR01")
	private String shvr01;

	@Column(name="SHVR02")
	private String shvr02;

	@Column(name="SHVR03")
	private String shvr03;

	@Column(name="SHVUMD")
	private String shvumd;

	@Column(name="SHWUMD")
	private String shwumd;

	@Column(name="SHZON")
	private String shzon;

	public F4201() {
	}

	public F4201PK getId() {
		return this.id;
	}

	public void setId(F4201PK id) {
		this.id = id;
	}

	public BigDecimal getShaddj() {
		return this.shaddj;
	}

	public void setShaddj(BigDecimal shaddj) {
		this.shaddj = shaddj;
	}

	public BigDecimal getShadlj() {
		return this.shadlj;
	}

	public void setShadlj(BigDecimal shadlj) {
		this.shadlj = shadlj;
	}

	public Double getShadtm() {
		return this.shadtm;
	}

	public void setShadtm(Double shadtm) {
		this.shadtm = shadtm;
	}

	public String getShaft() {
		return this.shaft;
	}

	public void setShaft(String shaft) {
		this.shaft = shaft;
	}

	public Double getShan8() {
		return this.shan8;
	}

	public void setShan8(Double shan8) {
		this.shan8 = shan8;
	}

	public Double getShanby() {
		return this.shanby;
	}

	public void setShanby(Double shanby) {
		this.shanby = shanby;
	}

	public String getShasn() {
		return this.shasn;
	}

	public void setShasn(String shasn) {
		this.shasn = shasn;
	}

	public String getShatxt() {
		return this.shatxt;
	}

	public void setShatxt(String shatxt) {
		this.shatxt = shatxt;
	}

	public String getShaufi() {
		return this.shaufi;
	}

	public void setShaufi(String shaufi) {
		this.shaufi = shaufi;
	}

	public String getShauft() {
		return this.shauft;
	}

	public void setShauft(String shauft) {
		this.shauft = shauft;
	}

	public String getShautn() {
		return this.shautn;
	}

	public void setShautn(String shautn) {
		this.shautn = shautn;
	}

	public String getShback() {
		return this.shback;
	}

	public void setShback(String shback) {
		this.shback = shback;
	}

	public String getShbcrc() {
		return this.shbcrc;
	}

	public void setShbcrc(String shbcrc) {
		this.shbcrc = shbcrc;
	}

	public String getShbsc() {
		return this.shbsc;
	}

	public void setShbsc(String shbsc) {
		this.shbsc = shbsc;
	}

	public String getShcact() {
		return this.shcact;
	}

	public void setShcact(String shcact) {
		this.shcact = shcact;
	}

	public Double getShcars() {
		return this.shcars;
	}

	public void setShcars(Double shcars) {
		this.shcars = shcars;
	}

	public Double getShccidln() {
		return this.shccidln;
	}

	public void setShccidln(Double shccidln) {
		this.shccidln = shccidln;
	}

	public BigDecimal getShcexp() {
		return this.shcexp;
	}

	public void setShcexp(BigDecimal shcexp) {
		this.shcexp = shcexp;
	}

	public BigDecimal getShcndj() {
		return this.shcndj;
	}

	public void setShcndj(BigDecimal shcndj) {
		this.shcndj = shcndj;
	}

	public String getShcnid() {
		return this.shcnid;
	}

	public void setShcnid(String shcnid) {
		this.shcnid = shcnid;
	}

	public String getShco() {
		return this.shco;
	}

	public void setShco(String shco) {
		this.shco = shco;
	}

	public Double getShcord() {
		return this.shcord;
	}

	public void setShcord(Double shcord) {
		this.shcord = shcord;
	}

	public String getShcot() {
		return this.shcot;
	}

	public void setShcot(String shcot) {
		this.shcot = shcot;
	}

	public String getShcrcd() {
		return this.shcrcd;
	}

	public void setShcrcd(String shcrcd) {
		this.shcrcd = shcrcd;
	}

	public String getShcrmd() {
		return this.shcrmd;
	}

	public void setShcrmd(String shcrmd) {
		this.shcrmd = shcrmd;
	}

	public Double getShcrr() {
		return this.shcrr;
	}

	public void setShcrr(Double shcrr) {
		this.shcrr = shcrr;
	}

	public String getShcrrm() {
		return this.shcrrm;
	}

	public void setShcrrm(String shcrrm) {
		this.shcrrm = shcrrm;
	}

	public String getShdct4() {
		return this.shdct4;
	}

	public void setShdct4(String shdct4) {
		this.shdct4 = shdct4;
	}

	public String getShdel1() {
		return this.shdel1;
	}

	public void setShdel1(String shdel1) {
		this.shdel1 = shdel1;
	}

	public String getShdel2() {
		return this.shdel2;
	}

	public void setShdel2(String shdel2) {
		this.shdel2 = shdel2;
	}

	public Double getShdoc1() {
		return this.shdoc1;
	}

	public void setShdoc1(Double shdoc1) {
		this.shdoc1 = shdoc1;
	}

	public BigDecimal getShdrqj() {
		return this.shdrqj;
	}

	public void setShdrqj(BigDecimal shdrqj) {
		this.shdrqj = shdrqj;
	}

	public Double getShdrqt() {
		return this.shdrqt;
	}

	public void setShdrqt(Double shdrqt) {
		this.shdrqt = shdrqt;
	}

	public Double getShdvan() {
		return this.shdvan;
	}

	public void setShdvan(Double shdvan) {
		this.shdvan = shdvan;
	}

	public String getShexr1() {
		return this.shexr1;
	}

	public void setShexr1(String shexr1) {
		this.shexr1 = shexr1;
	}

	public Double getShfap() {
		return this.shfap;
	}

	public void setShfap(Double shfap) {
		this.shfap = shfap;
	}

	public Double getShfcst() {
		return this.shfcst;
	}

	public void setShfcst(Double shfcst) {
		this.shfcst = shfcst;
	}

	public String getShfrtc() {
		return this.shfrtc;
	}

	public void setShfrtc(String shfrtc) {
		this.shfrtc = shfrtc;
	}

	public String getShfrth() {
		return this.shfrth;
	}

	public void setShfrth(String shfrth) {
		this.shfrth = shfrth;
	}

	public Double getShftan() {
		return this.shftan;
	}

	public void setShftan(Double shftan) {
		this.shftan = shftan;
	}

	public String getShfuf1() {
		return this.shfuf1;
	}

	public void setShfuf1(String shfuf1) {
		this.shfuf1 = shfuf1;
	}

	public String getShfuf2() {
		return this.shfuf2;
	}

	public void setShfuf2(String shfuf2) {
		this.shfuf2 = shfuf2;
	}

	public String getShhold() {
		return this.shhold;
	}

	public void setShhold(String shhold) {
		this.shhold = shhold;
	}

	public String getShinmg() {
		return this.shinmg;
	}

	public void setShinmg(String shinmg) {
		this.shinmg = shinmg;
	}

	public Double getShinvc() {
		return this.shinvc;
	}

	public void setShinvc(Double shinvc) {
		this.shinvc = shinvc;
	}

	public String getShir01() {
		return this.shir01;
	}

	public void setShir01(String shir01) {
		this.shir01 = shir01;
	}

	public String getShir02() {
		return this.shir02;
	}

	public void setShir02(String shir02) {
		this.shir02 = shir02;
	}

	public String getShir03() {
		return this.shir03;
	}

	public void setShir03(String shir03) {
		this.shir03 = shir03;
	}

	public String getShir04() {
		return this.shir04;
	}

	public void setShir04(String shir04) {
		this.shir04 = shir04;
	}

	public String getShir05() {
		return this.shir05;
	}

	public void setShir05(String shir05) {
		this.shir05 = shir05;
	}

	public Double getShitan() {
		return this.shitan;
	}

	public void setShitan(Double shitan) {
		this.shitan = shitan;
	}

	public String getShjobn() {
		return this.shjobn;
	}

	public void setShjobn(String shjobn) {
		this.shjobn = shjobn;
	}

	public String getShlngp() {
		return this.shlngp;
	}

	public void setShlngp(String shlngp) {
		this.shlngp = shlngp;
	}

	public String getShmcu() {
		return this.shmcu;
	}

	public void setShmcu(String shmcu) {
		this.shmcu = shmcu;
	}

	public String getShmord() {
		return this.shmord;
	}

	public void setShmord(String shmord) {
		this.shmord = shmord;
	}

	public String getShmot() {
		return this.shmot;
	}

	public void setShmot(String shmot) {
		this.shmot = shmot;
	}

	public String getShntr() {
		return this.shntr;
	}

	public void setShntr(String shntr) {
		this.shntr = shntr;
	}

	public String getShocto() {
		return this.shocto;
	}

	public void setShocto(String shocto) {
		this.shocto = shocto;
	}

	public String getShokco() {
		return this.shokco;
	}

	public void setShokco(String shokco) {
		this.shokco = shokco;
	}

	public String getShoorn() {
		return this.shoorn;
	}

	public void setShoorn(String shoorn) {
		this.shoorn = shoorn;
	}

	public String getShopba() {
		return this.shopba;
	}

	public void setShopba(String shopba) {
		this.shopba = shopba;
	}

	public Double getShopbk() {
		return this.shopbk;
	}

	public void setShopbk(Double shopbk) {
		this.shopbk = shopbk;
	}

	public String getShopbo() {
		return this.shopbo;
	}

	public void setShopbo(String shopbo) {
		this.shopbo = shopbo;
	}

	public BigDecimal getShopdj() {
		return this.shopdj;
	}

	public void setShopdj(BigDecimal shopdj) {
		this.shopdj = shopdj;
	}

	public BigDecimal getShopld() {
		return this.shopld;
	}

	public void setShopld(BigDecimal shopld) {
		this.shopld = shopld;
	}

	public String getShopll() {
		return this.shopll;
	}

	public void setShopll(String shopll) {
		this.shopll = shopll;
	}

	public String getShopms() {
		return this.shopms;
	}

	public void setShopms(String shopms) {
		this.shopms = shopms;
	}

	public Double getShoppid() {
		return this.shoppid;
	}

	public void setShoppid(Double shoppid) {
		this.shoppid = shoppid;
	}

	public String getShoppl() {
		return this.shoppl;
	}

	public void setShoppl(String shoppl) {
		this.shoppl = shoppl;
	}

	public String getShopps() {
		return this.shopps;
	}

	public void setShopps(String shopps) {
		this.shopps = shopps;
	}

	public Double getShopsb() {
		return this.shopsb;
	}

	public void setShopsb(Double shopsb) {
		this.shopsb = shopsb;
	}

	public String getShopss() {
		return this.shopss;
	}

	public void setShopss(String shopss) {
		this.shopss = shopss;
	}

	public Double getShoptc() {
		return this.shoptc;
	}

	public void setShoptc(Double shoptc) {
		this.shoptc = shoptc;
	}

	public Double getShoptt() {
		return this.shoptt;
	}

	public void setShoptt(Double shoptt) {
		this.shoptt = shoptt;
	}

	public String getShorby() {
		return this.shorby;
	}

	public void setShorby(String shorby) {
		this.shorby = shorby;
	}

	public String getShotind() {
		return this.shotind;
	}

	public void setShotind(String shotind) {
		this.shotind = shotind;
	}

	public Double getShotot() {
		return this.shotot;
	}

	public void setShotot(Double shotot) {
		this.shotot = shotot;
	}

	public Double getShpa8() {
		return this.shpa8;
	}

	public void setShpa8(Double shpa8) {
		this.shpa8 = shpa8;
	}

	public Double getShpban() {
		return this.shpban;
	}

	public void setShpban(Double shpban) {
		this.shpban = shpban;
	}

	public Double getShpcrt() {
		return this.shpcrt;
	}

	public void setShpcrt(Double shpcrt) {
		this.shpcrt = shpcrt;
	}

	public BigDecimal getShpddj() {
		return this.shpddj;
	}

	public void setShpddj(BigDecimal shpddj) {
		this.shpddj = shpddj;
	}

	public Double getShpdtt() {
		return this.shpdtt;
	}

	public void setShpdtt(Double shpdtt) {
		this.shpdtt = shpdtt;
	}

	public BigDecimal getShpefj() {
		return this.shpefj;
	}

	public void setShpefj(BigDecimal shpefj) {
		this.shpefj = shpefj;
	}

	public String getShpid() {
		return this.shpid;
	}

	public void setShpid(String shpid) {
		this.shpid = shpid;
	}

	public String getShplst() {
		return this.shplst;
	}

	public void setShplst(String shplst) {
		this.shplst = shplst;
	}

	public Double getShpmdt() {
		return this.shpmdt;
	}

	public void setShpmdt(Double shpmdt) {
		this.shpmdt = shpmdt;
	}

	public BigDecimal getShppdj() {
		return this.shppdj;
	}

	public void setShppdj(BigDecimal shppdj) {
		this.shppdj = shppdj;
	}

	public Double getShpran8() {
		return this.shpran8;
	}

	public void setShpran8(Double shpran8) {
		this.shpran8 = shpran8;
	}

	public Double getShprcidln() {
		return this.shprcidln;
	}

	public void setShprcidln(Double shprcidln) {
		this.shprcidln = shprcidln;
	}

	public String getShprgp() {
		return this.shprgp;
	}

	public void setShprgp(String shprgp) {
		this.shprgp = shprgp;
	}

	public String getShprio() {
		return this.shprio;
	}

	public void setShprio(String shprio) {
		this.shprio = shprio;
	}

	public Double getShpstm() {
		return this.shpstm;
	}

	public void setShpstm(Double shpstm) {
		this.shpstm = shpstm;
	}

	public String getShptc() {
		return this.shptc;
	}

	public void setShptc(String shptc) {
		this.shptc = shptc;
	}

	public String getShrcd() {
		return this.shrcd;
	}

	public void setShrcd(String shrcd) {
		this.shrcd = shrcd;
	}

	public String getShrcto() {
		return this.shrcto;
	}

	public void setShrcto(String shrcto) {
		this.shrcto = shrcto;
	}

	public String getShrkco() {
		return this.shrkco;
	}

	public void setShrkco(String shrkco) {
		this.shrkco = shrkco;
	}

	public String getShrorn() {
		return this.shrorn;
	}

	public void setShrorn(String shrorn) {
		this.shrorn = shrorn;
	}

	public String getShrout() {
		return this.shrout;
	}

	public void setShrout(String shrout) {
		this.shrout = shrout;
	}

	public BigDecimal getShrqsj() {
		return this.shrqsj;
	}

	public void setShrqsj(BigDecimal shrqsj) {
		this.shrqsj = shrqsj;
	}

	public Double getShrsdt() {
		return this.shrsdt;
	}

	public void setShrsdt(Double shrsdt) {
		this.shrsdt = shrsdt;
	}

	public String getShryin() {
		return this.shryin;
	}

	public void setShryin(String shryin) {
		this.shryin = shryin;
	}

	public String getShsbal() {
		return this.shsbal;
	}

	public void setShsbal(String shsbal) {
		this.shsbal = shsbal;
	}

	public String getShsbli() {
		return this.shsbli;
	}

	public void setShsbli(String shsbli) {
		this.shsbli = shsbli;
	}

	public String getShsdattn() {
		return this.shsdattn;
	}

	public void setShsdattn(String shsdattn) {
		this.shsdattn = shsdattn;
	}

	public String getShsfxo() {
		return this.shsfxo;
	}

	public void setShsfxo(String shsfxo) {
		this.shsfxo = shsfxo;
	}

	public Double getShshan() {
		return this.shshan;
	}

	public void setShshan(Double shshan) {
		this.shshan = shshan;
	}

	public Double getShshccidln() {
		return this.shshccidln;
	}

	public void setShshccidln(Double shshccidln) {
		this.shshccidln = shshccidln;
	}

	public BigDecimal getShsoor() {
		return this.shsoor;
	}

	public void setShsoor(BigDecimal shsoor) {
		this.shsoor = shsoor;
	}

	public String getShspattn() {
		return this.shspattn;
	}

	public void setShspattn(String shspattn) {
		this.shspattn = shspattn;
	}

	public String getShstop() {
		return this.shstop;
	}

	public void setShstop(String shstop) {
		this.shstop = shstop;
	}

	public Double getShtday() {
		return this.shtday;
	}

	public void setShtday(Double shtday) {
		this.shtday = shtday;
	}

	public String getShtkby() {
		return this.shtkby;
	}

	public void setShtkby(String shtkby) {
		this.shtkby = shtkby;
	}

	public Double getShtotc() {
		return this.shtotc;
	}

	public void setShtotc(Double shtotc) {
		this.shtotc = shtotc;
	}

	public Double getShtrdc() {
		return this.shtrdc;
	}

	public void setShtrdc(Double shtrdc) {
		this.shtrdc = shtrdc;
	}

	public BigDecimal getShtrdj() {
		return this.shtrdj;
	}

	public void setShtrdj(BigDecimal shtrdj) {
		this.shtrdj = shtrdj;
	}

	public String getShtxa1() {
		return this.shtxa1;
	}

	public void setShtxa1(String shtxa1) {
		this.shtxa1 = shtxa1;
	}

	public String getShtxct() {
		return this.shtxct;
	}

	public void setShtxct(String shtxct) {
		this.shtxct = shtxct;
	}

	public BigDecimal getShupmj() {
		return this.shupmj;
	}

	public void setShupmj(BigDecimal shupmj) {
		this.shupmj = shupmj;
	}

	public Double getShurab() {
		return this.shurab;
	}

	public void setShurab(Double shurab) {
		this.shurab = shurab;
	}

	public Double getShurat() {
		return this.shurat;
	}

	public void setShurat(Double shurat) {
		this.shurat = shurat;
	}

	public String getShurcd() {
		return this.shurcd;
	}

	public void setShurcd(String shurcd) {
		this.shurcd = shurcd;
	}

	public BigDecimal getShurdt() {
		return this.shurdt;
	}

	public void setShurdt(BigDecimal shurdt) {
		this.shurdt = shurdt;
	}

	public String getShurrf() {
		return this.shurrf;
	}

	public void setShurrf(String shurrf) {
		this.shurrf = shurrf;
	}

	public String getShuser() {
		return this.shuser;
	}

	public void setShuser(String shuser) {
		this.shuser = shuser;
	}

	public String getShvr01() {
		return this.shvr01;
	}

	public void setShvr01(String shvr01) {
		this.shvr01 = shvr01;
	}

	public String getShvr02() {
		return this.shvr02;
	}

	public void setShvr02(String shvr02) {
		this.shvr02 = shvr02;
	}

	public String getShvr03() {
		return this.shvr03;
	}

	public void setShvr03(String shvr03) {
		this.shvr03 = shvr03;
	}

	public String getShvumd() {
		return this.shvumd;
	}

	public void setShvumd(String shvumd) {
		this.shvumd = shvumd;
	}

	public String getShwumd() {
		return this.shwumd;
	}

	public void setShwumd(String shwumd) {
		this.shwumd = shwumd;
	}

	public String getShzon() {
		return this.shzon;
	}

	public void setShzon(String shzon) {
		this.shzon = shzon;
	}

}