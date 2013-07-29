;; The contents of this file are subject to the LGPL License, Version 3.0.
;;
;; Copyright (C) 2013, Phillip Lord, Newcastle University
;;
;; This program is free software: you can redistribute it and/or modify it
;; under the terms of the GNU Lesser General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or (at your
;; option) any later version.
;;
;; This program is distributed in the hope that it will be useful, but WITHOUT
;; ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
;; FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
;; for more details.
;;
;; You should have received a copy of the GNU Lesser General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.
(ns simple.range-test
  (:use [clojure.test])
  (:use [tawny.owl])
  (:require [tawny.reasoner :as r])
  (:require [simple.range :as rn]))

(use-fixtures :once (tawny.fixture/namespace-and-reasoner
                     'simple.range :hermit))

(deftest X
  (is (= #{rn/i}
         (r/instances rn/X))))

(deftest A
  (is (= #{}
         (r/isuperclasses rn/A))))