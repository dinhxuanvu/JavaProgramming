/**
 * WeatherLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cdyne.ws.WeatherWS;

public class WeatherLocator extends org.apache.axis.client.Service implements com.cdyne.ws.WeatherWS.Weather {

    public WeatherLocator() {
    }


    public WeatherLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WeatherLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WeatherSoap12
    private java.lang.String WeatherSoap12_address = "http://wsf.cdyne.com/WeatherWS/Weather.asmx";

    public java.lang.String getWeatherSoap12Address() {
        return WeatherSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WeatherSoap12WSDDServiceName = "WeatherSoap12";

    public java.lang.String getWeatherSoap12WSDDServiceName() {
        return WeatherSoap12WSDDServiceName;
    }

    public void setWeatherSoap12WSDDServiceName(java.lang.String name) {
        WeatherSoap12WSDDServiceName = name;
    }

    public com.cdyne.ws.WeatherWS.WeatherSoap getWeatherSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WeatherSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWeatherSoap12(endpoint);
    }

    public com.cdyne.ws.WeatherWS.WeatherSoap getWeatherSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cdyne.ws.WeatherWS.WeatherSoap12Stub _stub = new com.cdyne.ws.WeatherWS.WeatherSoap12Stub(portAddress, this);
            _stub.setPortName(getWeatherSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWeatherSoap12EndpointAddress(java.lang.String address) {
        WeatherSoap12_address = address;
    }


    // Use to get a proxy class for WeatherSoap
    private java.lang.String WeatherSoap_address = "http://wsf.cdyne.com/WeatherWS/Weather.asmx";

    public java.lang.String getWeatherSoapAddress() {
        return WeatherSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WeatherSoapWSDDServiceName = "WeatherSoap";

    public java.lang.String getWeatherSoapWSDDServiceName() {
        return WeatherSoapWSDDServiceName;
    }

    public void setWeatherSoapWSDDServiceName(java.lang.String name) {
        WeatherSoapWSDDServiceName = name;
    }

    public com.cdyne.ws.WeatherWS.WeatherSoap getWeatherSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WeatherSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWeatherSoap(endpoint);
    }

    public com.cdyne.ws.WeatherWS.WeatherSoap getWeatherSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cdyne.ws.WeatherWS.WeatherSoapStub _stub = new com.cdyne.ws.WeatherWS.WeatherSoapStub(portAddress, this);
            _stub.setPortName(getWeatherSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWeatherSoapEndpointAddress(java.lang.String address) {
        WeatherSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cdyne.ws.WeatherWS.WeatherSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cdyne.ws.WeatherWS.WeatherSoap12Stub _stub = new com.cdyne.ws.WeatherWS.WeatherSoap12Stub(new java.net.URL(WeatherSoap12_address), this);
                _stub.setPortName(getWeatherSoap12WSDDServiceName());
                return _stub;
            }
            if (com.cdyne.ws.WeatherWS.WeatherSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cdyne.ws.WeatherWS.WeatherSoapStub _stub = new com.cdyne.ws.WeatherWS.WeatherSoapStub(new java.net.URL(WeatherSoap_address), this);
                _stub.setPortName(getWeatherSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WeatherSoap12".equals(inputPortName)) {
            return getWeatherSoap12();
        }
        else if ("WeatherSoap".equals(inputPortName)) {
            return getWeatherSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.cdyne.com/WeatherWS/", "Weather");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.cdyne.com/WeatherWS/", "WeatherSoap12"));
            ports.add(new javax.xml.namespace.QName("http://ws.cdyne.com/WeatherWS/", "WeatherSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WeatherSoap12".equals(portName)) {
            setWeatherSoap12EndpointAddress(address);
        }
        else 
if ("WeatherSoap".equals(portName)) {
            setWeatherSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
