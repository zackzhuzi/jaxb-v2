/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://jwsdp.dev.java.net/CDDLv1.0.html
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * https://jwsdp.dev.java.net/CDDLv1.0.html  If applicable,
 * add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your
 * own identifying information: Portions Copyright [yyyy]
 * [name of copyright owner]
 */
package com.sun.xml.xsom;

import java.util.List;


/**
 * Complex type.
 * 
 * @author
 *  Kohsuke Kawaguchi (kohsuke.kawaguchi@sun.com)
 */
public interface XSComplexType extends XSType, XSAttContainer
{
    /**
     * Checks if this complex type is declared as an abstract type.
     */
    boolean isAbstract();

    boolean isFinal(int derivationMethod);
    /**
     * Roughly corresponds to the block attribute. But see the spec
     * for gory detail.
     */
    boolean isSubstitutionProhibited(int method);
    
    /**
     * Gets the scope of this complex type.
     * This is not a property defined in the schema spec.
     * 
     * @return
     *      null if this complex type is global. Otherwise
     *      return the element declaration that contains this anonymous
     *      complex type.
     */
    XSElementDecl getScope();

    /**
     * The content of this complex type.
     * 
     * @return
     *      always non-null.
     */
    XSContentType getContentType();
    
    /**
     * Gets the explicit content of a complex type with a complex content
     * that was derived by extension.
     * 
     * <p>
     * Informally, the "explicit content" is the portion of the 
     * content model added in this derivation. IOW, it's a delta between
     * the base complex type and this complex type.
     * 
     * <p>
     * For example, when a complex type T2 derives fom T1, then:
     * <pre>
     * content type of T2 = SEQUENCE( content type of T1, explicit content of T2 )
     * </pre>
     * 
     * @return
     *      If this complex type is derived by restriction or has a
     *      simple content, this method returns null.
     *      IOW, this method only works for a complex type with
     *      a complex content derived by extension from another complex type.
     */
    XSContentType getExplicitContent();

    // meaningful only if getContentType returns particles
    boolean isMixed();

    /**
     * If this {@link XSComplexType} is redefined by another complex type,
     * return that component.
     *
     * @return null
     *      if this component has not been redefined.
     */
    public XSComplexType getRedefinedBy();

    public List<XSComplexType> getSubtypes();
}