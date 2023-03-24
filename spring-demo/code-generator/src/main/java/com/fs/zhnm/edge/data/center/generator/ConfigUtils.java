/*
 * Copyright (c) 2021-2030 丰尚智慧农牧科技有限公司
 * 不能修改和删除上面的版权声明
 * 此代码属于丰尚智慧农牧科技有限公司编写，在未经允许的情况下不得传播复制
 */
package com.fs.zhnm.edge.data.center.generator;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * 配置工具类
 *
 * @author Liam
 * @date 2021-08-11 09:50:34
 */
public class ConfigUtils {
    protected class Config {
        private String driverName;
        private String dbUserName;
        private String dbPassword;
        private String dbLink;
        private String database;
        private String tableNames;
        private String poSourceFolder;
        private String poPackage;
        private String boSourceFolder;
        private String boPackage;
        private String daoSourceFolder;
        private String daoPackage;
        private String author;

        /**
         * @param database the database to set
         */
        public void setDatabase(String database) {
            this.database = database;
        }

        /**
         * @return the driverName
         */
        public String getDriverName() {
            return driverName;
        }

        /**
         * @param driverName the driverName to set
         */
        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        /**
         * @return the dbUserName
         */
        public String getDbUserName() {
            return dbUserName;
        }

        /**
         * @param dbUserName the dbUserName to set
         */
        public void setDbUserName(String dbUserName) {
            this.dbUserName = dbUserName;
        }

        /**
         * @return the dbPassword
         */
        public String getDbPassword() {
            return dbPassword;
        }

        /**
         * @param dbPassword the dbPassword to set
         */
        public void setDbPassword(String dbPassword) {
            this.dbPassword = dbPassword;
        }

        /**
         * @return the dbLink
         */
        public String getDbLink() {
            return dbLink;
        }

        /**
         * @param dbLink the dbLink to set
         */
        public void setDbLink(String dbLink) {
            this.dbLink = dbLink;
        }

        /**
         * @return the database
         */
        public String getDatabase() {
            return database;
        }

        /**
         * @return the tableNames
         */
        public String getTableNames() {
            return tableNames;
        }

        /**
         * @param tableNames the tableNames to set
         */
        public void setTableNames(String tableNames) {
            this.tableNames = tableNames;
        }

        /**
         * @return the poSourceFolder
         */
        public String getPoSourceFolder() {
            return poSourceFolder;
        }

        /**
         * @param poSourceFolder the poSourceFolder to set
         */
        public void setPoSourceFolder(String poSourceFolder) {
            this.poSourceFolder = poSourceFolder;
        }

        /**
         * @return the poPackage
         */
        public String getPoPackage() {
            return poPackage;
        }

        /**
         * @param poPackage the poPackage to set
         */
        public void setPoPackage(String poPackage) {
            this.poPackage = poPackage;
        }

        /**
         * @return the boSourceFolder
         */
        public String getBoSourceFolder() {
            return boSourceFolder;
        }

        /**
         * @param boSourceFolder the boSourceFolder to set
         */
        public void setBoSourceFolder(String boSourceFolder) {
            this.boSourceFolder = boSourceFolder;
        }

        /**
         * @return the boPackage
         */
        public String getBoPackage() {
            return boPackage;
        }

        /**
         * @param boPackage the boPackage to set
         */
        public void setBoPackage(String boPackage) {
            this.boPackage = boPackage;
        }

        /**
         * @return the daoSourceFolder
         */
        public String getDaoSourceFolder() {
            return daoSourceFolder;
        }

        /**
         * @param daoSourceFolder the daoSourceFolder to set
         */
        public void setDaoSourceFolder(String daoSourceFolder) {
            this.daoSourceFolder = daoSourceFolder;
        }

        /**
         * @return the daoPackage
         */
        public String getDaoPackage() {
            return daoPackage;
        }

        /**
         * @param daoPackage the daoPackage to set
         */
        public void setDaoPackage(String daoPackage) {
            this.daoPackage = daoPackage;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }

    private static Config config;

    public static Config getConfig() {
        try {
            if (null == config) {
                String configStr = IOUtils.toString(ConfigUtils.class.getClassLoader().getResourceAsStream("table.json"),
                        "UTF-8");
                config = new Gson().fromJson(configStr, Config.class);
            }

            return config;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
