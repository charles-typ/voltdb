/* This file is part of VoltDB.
 * Copyright (C) 2008-2020 VoltDB Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with VoltDB.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.voltdb;

import java.util.EnumSet;
import java.util.Map;
import java.util.regex.Pattern;

import com.google_voltpatches.common.collect.ImmutableMap;

public enum StartAction {

    CREATE("create", false, null),
    RECOVER("recover", false, "Command Log Recovery"),
    SAFE_RECOVER("recover safemode", true, "Command Log Recovery"),
    REJOIN("rejoin", false, "K-Safety / Node Rejoin"),
    LIVE_REJOIN("live rejoin", false, "K-Safety / Node Rejoin"),
    JOIN("add", true, "Elastic Cluster Sizing"),
    INITIALIZE("initialize", false, "Layout and prime voltdbroot"),
    PROBE("probe", false, "Determine start action"),
    GET("get", false, "Get Configuration");

    final static Pattern spaces = Pattern.compile("\\s+");

    final static Map<String, StartAction> verbMoniker;

    final static EnumSet<StartAction> recoverSet =
            EnumSet.of(RECOVER,SAFE_RECOVER);

    final static EnumSet<StartAction> rejoinSet =
            EnumSet.of(REJOIN,LIVE_REJOIN);

    final static EnumSet<StartAction> joinSet =
            EnumSet.of(REJOIN,LIVE_REJOIN,JOIN);

    final static EnumSet<StartAction> requireEmptyDirsSet =
            EnumSet.of(CREATE);

    final static EnumSet<StartAction> legacySet =
            EnumSet.complementOf(EnumSet.of(INITIALIZE,PROBE,GET));

    final String m_verb;
    final boolean m_enterpriseOnly;
    final String m_featureNameForErrorString;

    static {
        ImmutableMap.Builder<String, StartAction> mb = ImmutableMap.builder();
        for (StartAction action: StartAction.values()) {
            mb.put(action.m_verb, action);
        }
        verbMoniker = mb.build();
    }

    StartAction(String verb, boolean enterpriseOnly, String featureNameForErrorString) {
        m_verb = verb;
        m_enterpriseOnly = enterpriseOnly;
        m_featureNameForErrorString = featureNameForErrorString;
    }

    public static StartAction monickerFor(String verb) {
        if (verb == null) return null;
        verb = spaces.matcher(verb.trim().toLowerCase()).replaceAll(" ");
        return verbMoniker.get(verb);
    }

    public String verb() {
        return m_verb;
    }

    public boolean isEnterpriseOnly() {
        return m_enterpriseOnly;
    }

    public String featureNameForErrorString() {
        return m_featureNameForErrorString;
    }

    public boolean doesRecover() {
        return recoverSet.contains(this);
    }

    public boolean doesRejoin() {
        return rejoinSet.contains(this);
    }

    public boolean doesJoin() {
        return joinSet.contains(this);
    }

    public boolean isLegacy() {
        return legacySet.contains(this);
    }

    public boolean doesRequireEmptyDirectories() {
        return requireEmptyDirsSet.contains(this);
    }
}
