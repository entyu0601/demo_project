package com.example.demo_project.entity;

public class Calculator2 {
	int first_number, sceound_number;
	int Add, Minus, Multi, Division, Mod;

	public void Calculator(int first_number, int sceound_number) {
		this.first_number = first_number;
		this.sceound_number = sceound_number;
	}

	public int getFirst_number() {
		return first_number;
	}

	public void setFirst_number(int first_number) {
		this.first_number = first_number;
	}

	public int getSceound_number() {
		return sceound_number;
	}

	public void setSceound_number(int sceound_number) {
		this.sceound_number = sceound_number;
	}

	public int getAdd() {
		return Add;
	}

	public void setAdd(int add) {
		Add = add;
	}

	public int getMinus() {
		return Minus;
	}

	public void setMinus(int minus) {
		Minus = minus;
	}

	public int getMulti() {
		return Multi;
	}

	public void setMulti(int multi) {
		Multi = multi;
	}

	public int getDivision() {
		return Division;
	}

	public void setDivision(int division) {
		Division = division;
	}

	public int getMod() {
		return Mod;
	}

	public void setMod(int mod) {
		Mod = mod;
	}

}
