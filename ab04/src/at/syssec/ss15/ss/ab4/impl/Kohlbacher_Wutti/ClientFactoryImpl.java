package at.syssec.ss15.ss.ab4.impl.Kohlbacher_Wutti;

import at.syssec.ss15.ss.ab4.CRClientA;
import at.syssec.ss15.ss.ab4.CRClientB;
import at.syssec.ss15.ss.ab4.ClientFactory;
import at.syssec.ss15.ss.ab4.DHClientA;
import at.syssec.ss15.ss.ab4.DHClientB;

public class ClientFactoryImpl implements ClientFactory {

	@Override
	public CRClientA generateCRClientA() {
		return null;
	}

	@Override
	public CRClientB generateCRClientB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DHClientA generateDHClientA() {
		return new DHClientAImpl();
	}

	@Override
	public DHClientB generateDHClientB() {
		return new DHClientBImpl();
	}

}
